package xyz.jzab.initDemo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.jzab.common.enums.UserRoles;
import xyz.jzab.common.enums.UserStatus;

import java.util.Date;

/**
 * @author JZAB
 */
@Data
public class UserVo {
    private Long id;

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
