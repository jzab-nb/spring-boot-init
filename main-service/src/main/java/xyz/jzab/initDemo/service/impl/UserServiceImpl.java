package xyz.jzab.initDemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.jzab.initDemo.domain.po.User;
import xyz.jzab.initDemo.service.UserService;
import xyz.jzab.initDemo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 86131
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-11-20 20:02:02
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




