package xyz.jzab.initDemo.domain.dto.user;

import lombok.Data;
import xyz.jzab.common.enums.UserRoles;
import xyz.jzab.common.enums.UserStatus;

/**
 * @author JZAB
 */
@Data
public class UserUpdateRequest {
    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private UserRoles role;

    /**
     * 状态
     */
    private UserStatus userStatus;
}
