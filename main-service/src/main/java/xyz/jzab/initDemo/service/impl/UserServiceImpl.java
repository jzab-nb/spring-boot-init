package xyz.jzab.initDemo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.jzab.initDemo.domain.dto.user.UserAddRequest;
import xyz.jzab.initDemo.domain.po.User;
import xyz.jzab.initDemo.service.UserService;
import xyz.jzab.initDemo.mapper.UserMapper;
import org.springframework.stereotype.Service;

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
            throw new RuntimeException("用户名重复");
        }
        User user = BeanUtil.toBean(request, User.class);
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        user.setPassword(MD5.create( ).digestHex16(user.getPassword()+salt ));
        this.save(user);
    }
}




