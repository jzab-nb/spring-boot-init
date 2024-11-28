package xyz.jzab.initDemo.domain.dto.user;

import lombok.Data;

/**
 * @author JZAB
 */
@Data
public class UserRegisterRequest {
    private String username;

    /**
     * 密码
     */
    private String password;

    private String password2;
}
