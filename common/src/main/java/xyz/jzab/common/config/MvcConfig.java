package xyz.jzab.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.jzab.common.interceptor.UserIdInterceptor;
import xyz.jzab.common.utils.JwtTool;

/**
 * @author JZAB
 */
@Configuration
@ConditionalOnClass(DispatcherServlet.class)
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final JwtTool jwtTool;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserIdInterceptor(jwtTool));
    }
}
