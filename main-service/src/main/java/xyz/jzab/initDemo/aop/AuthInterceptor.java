package xyz.jzab.initDemo.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import xyz.jzab.common.annotation.AuthCheck;
import xyz.jzab.common.enums.RespCode;
import xyz.jzab.common.exception.BusinessException;
import xyz.jzab.common.utils.UserContext;
import xyz.jzab.initDemo.domain.po.User;
import xyz.jzab.initDemo.service.UserService;

import java.util.Arrays;

/**
 * @author JZAB
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthInterceptor {

    private final UserService userService;
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        Long userId = UserContext.getUser( );
        if(userId==null) throw new BusinessException(RespCode.NOT_LOGIN);

        User user = userService.getById(userId);
        if(user==null) throw new BusinessException(RespCode.NOT_LOGIN,"登录用户不存在");
        // 不需要权限,或者当前用户有权限,则放行
        if (authCheck.mustRole()==null || authCheck.mustRole().length==0 || Arrays.asList(authCheck.mustRole()).contains(user.getRole())) {
            return joinPoint.proceed( );
        // 否则就是权限不足
        }else{
            throw new BusinessException(RespCode.NO_AUTH_ERROR);
        }
    }
}
