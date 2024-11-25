package xyz.jzab.common.exception;

import lombok.Getter;
import xyz.jzab.common.enums.RespCode;

/**
 * @author JZAB
 */
@Getter
public class BusinessException extends RuntimeException{
    private final RespCode code;

    public BusinessException(RespCode respCode){
        super(respCode.getMsg());
        this.code = respCode;
    }

    public BusinessException(RespCode respCode,String msg){
        super(msg);
        this.code = respCode;
    }

}
