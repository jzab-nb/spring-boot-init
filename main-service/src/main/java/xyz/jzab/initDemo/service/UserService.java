package xyz.jzab.initDemo.service;

import xyz.jzab.initDemo.domain.dto.user.UserAddRequest;
import xyz.jzab.initDemo.domain.dto.user.UserLoginRequest;
import xyz.jzab.initDemo.domain.dto.user.UserRegisterRequest;
import xyz.jzab.initDemo.domain.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.jzab.initDemo.domain.vo.UserLoginVo;

/**
* @author 86131
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-11-20 20:02:02
*/
public interface UserService extends IService<User> {
    void addUser(UserAddRequest request);

    UserLoginVo login(UserLoginRequest request);
    UserLoginVo register(UserRegisterRequest request);
}
