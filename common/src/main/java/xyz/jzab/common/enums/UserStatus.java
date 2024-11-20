package xyz.jzab.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE(1,"激活"),
    BAN(0,"禁用");

    @EnumValue
    private final int code;
    @JsonValue
    private final String desc;

    UserStatus(int code,String desc){
        this.code = code;
        this.desc = desc;
    }
}
