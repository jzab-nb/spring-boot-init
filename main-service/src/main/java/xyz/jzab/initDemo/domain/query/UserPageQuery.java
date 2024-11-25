package xyz.jzab.initDemo.domain.query;

import xyz.jzab.common.domain.PageRequest;
import xyz.jzab.common.enums.UserRoles;
import xyz.jzab.common.enums.UserStatus;

/**
 * @author JZAB
 */
public class UserPageQuery extends PageRequest {
    /**
     * 用户名
     */
    private String username;

    /**
     * 角色
     */
    private UserRoles role;

    /**
     * 状态
     */
    private UserStatus userStatus;
}
