package xyz.jzab.initDemo.domain.dto.user;

import lombok.Data;

/**
 * @author JZAB
 */
@Data
public class UserLoginRequest {
    private String username;

    /**
     * 密码
     */
    private String password;
}
