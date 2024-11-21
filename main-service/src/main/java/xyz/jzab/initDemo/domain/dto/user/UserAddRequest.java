package xyz.jzab.initDemo.domain.dto.user;

import lombok.Data;
import xyz.jzab.common.enums.UserRoles;
import xyz.jzab.common.enums.UserStatus;

/**
 * @author JZAB
 */
@Data
public class UserAddRequest {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private UserRoles role;
}
