package xyz.jzab.common.domain;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import xyz.jzab.common.enums.RespCode;

/**
 * @author JZAB
 * 基础返回类
 */
public class BaseResponse<T> extends ResponseEntity<BaseResponse.InnerResponse> {
    // 建造者类,负责动态构建三个参数
    public static class Builder{
        private String msg;
        private RespCode code=RespCode.OK;

        private Builder(){}

        public Builder msg(String msg){
            this.msg = msg;
            return this;
        }

        public Builder code(RespCode code){
            if(msg==null) this.msg = code.getMsg();
            this.code = code;
            return this;
        }

        public BaseResponse<Void> build(){
            return new BaseResponse<Void>(msg,null,code);
        }

        public <T> BaseResponse<T> build(T body){
            return new BaseResponse<T>(msg,body,code);
        }

        public <T> BaseResponse<T> body(T body){
            return build(body);
        }
    }

    // 成功的简化调用
    public static Builder success(){
        return new Builder().code(RespCode.OK);
    }

    public static <T> BaseResponse<T> success(T body){
        return new Builder().code(RespCode.OK).body(body);
    }

    // 失败的简化调用
    public static Builder error(){
        return new Builder().code(RespCode.FAIL);
    }

    public static <T> BaseResponse<T> error(T body){
        return new Builder().code(RespCode.FAIL).body(body);
    }

    // 自定义响应状态码的调用
    public static Builder builder(RespCode code){
        return new Builder().code(code);
    }

    // 内部类,主要负责封装{code,msg,body}三个字段
    @Getter
    static class InnerResponse<T>{
        private final String msg;
        private final T body;
        private final int code;

        private InnerResponse(String msg,T body,int code){
            this.msg = msg;
            this.body = body;
            this.code = code;
        }

        public static <T> InnerResponse<T> of(String msg, T body, int code){
            return new InnerResponse<T>(msg,body,code);
        }
    }

    // 构造器,调用父类构造器实现标准的http状态码
    public BaseResponse(String msg,T body, RespCode code) {
        super(InnerResponse.of(msg,body,code.getCode()),code.getHttpStatus());
    }
}