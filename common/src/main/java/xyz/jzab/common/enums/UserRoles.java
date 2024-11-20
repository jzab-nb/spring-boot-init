package xyz.jzab.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserRoles {
    ADMIN(0,"管理员"),
    USER(1,"普通用户");

    @EnumValue // 定义枚举类型在数据库中存在的值
    private final int code;
    @JsonValue // 定义枚举类型返回给钱简单的值
    private final String desc;

    UserRoles(int code,String desc){
        this.code = code;
        this.desc = desc;
    }
}
