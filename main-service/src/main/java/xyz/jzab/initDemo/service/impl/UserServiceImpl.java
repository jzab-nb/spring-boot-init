package xyz.jzab.initDemo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.jzab.common.enums.RespCode;
import xyz.jzab.common.enums.UserStatus;
import xyz.jzab.common.exception.BusinessException;
import xyz.jzab.initDemo.domain.dto.user.UserAddRequest;
import xyz.jzab.initDemo.domain.po.User;
import xyz.jzab.initDemo.mapper.UserMapper;
import xyz.jzab.initDemo.service.UserService;

import java.util.UUID;

/**
* @author 86131
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-11-20 20:02:02
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    @Override
    public void addUser(UserAddRequest request) {
        if(!lambdaQuery().eq(User::getUsername,request.getUsername()).list().isEmpty()){
            throw new BusinessException(RespCode.CONFLICT,"用户名重复");
        }
        User user = BeanUtil.toBean(request, User.class);
        String salt = UUID.randomUUID().toString();
        user.setUserStatus(UserStatus.ACTIVE);
        user.setSalt(salt);
        user.digestPassword();
        this.save(user);
    }
}




