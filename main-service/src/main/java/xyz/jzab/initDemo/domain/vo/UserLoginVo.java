package xyz.jzab.initDemo.domain.vo;

import lombok.Data;
import xyz.jzab.common.enums.UserRoles;

/**
 * @author JZAB
 */
@Data
public class UserLoginVo {
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 角色
     */
    private UserRoles role;

    private String token;
}
