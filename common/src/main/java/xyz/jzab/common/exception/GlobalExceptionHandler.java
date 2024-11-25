package xyz.jzab.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;
import xyz.jzab.common.domain.BaseResponse;

/**
 * @author JZAB
 * 全局异常处理类
 */
@RestControllerAdvice
@Slf4j
// 当有SpringMVC时才引入当前类
@ConditionalOnClass(DispatcherServlet.class)
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e){
        log.error("业务异常: ",e);
        return BaseResponse.builder(e.getCode()).msg(e.getMessage()).build();
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e){
        log.error("系统运行时异常: ",e);
        return BaseResponse.error().msg(e.getMessage()).build();
    }
}
