package xyz.jzab.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum RespCode {
    OK(200,"OK",HttpStatus.OK),
    CREATED(201,"已创建",HttpStatus.CREATED),
    NOT_FOUND(404,"请求的资源不存在",HttpStatus.NOT_FOUND),
    FAIL(500,"失败",HttpStatus.INTERNAL_SERVER_ERROR);
    private final int code;
    private final String msg;
    private final HttpStatus httpStatus;

    RespCode(int code,String msg,HttpStatus httpStatus){
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }
}
