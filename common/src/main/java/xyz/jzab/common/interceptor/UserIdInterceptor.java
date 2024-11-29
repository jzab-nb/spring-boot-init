package xyz.jzab.common.interceptor;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.jzab.common.exception.BusinessException;
import xyz.jzab.common.utils.JwtTool;
import xyz.jzab.common.utils.UserContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JZAB
 */
@RequiredArgsConstructor
@Slf4j
public class UserIdInterceptor implements HandlerInterceptor {

    private final JwtTool jwtTool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 读取用户信息并保存到
        String token = request.getHeader("token");
        if(StrUtil.isNotBlank(token)){
            Long id = jwtTool.parseToken(token);
            log.info("解析token,用户{}来访",id);
            UserContext.setUser(id);
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理用户
        UserContext.removeUser();
    }
}
