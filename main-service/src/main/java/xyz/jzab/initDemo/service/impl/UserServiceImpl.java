package xyz.jzab.initDemo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.jzab.common.enums.RespCode;
import xyz.jzab.common.enums.UserRoles;
import xyz.jzab.common.enums.UserStatus;
import xyz.jzab.common.exception.BusinessException;
import xyz.jzab.common.utils.BeanUtils;
import xyz.jzab.common.utils.JwtTool;
import xyz.jzab.initDemo.domain.dto.user.UserAddRequest;
import xyz.jzab.initDemo.domain.dto.user.UserLoginRequest;
import xyz.jzab.initDemo.domain.dto.user.UserRegisterRequest;
import xyz.jzab.initDemo.domain.po.User;
import xyz.jzab.initDemo.domain.vo.UserLoginVo;
import xyz.jzab.initDemo.domain.vo.UserVo;
import xyz.jzab.initDemo.mapper.UserMapper;
import xyz.jzab.initDemo.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
* @author 86131
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-11-20 20:02:02
*/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    private final JwtTool jwtTool;

    @Override
    public void addUser(UserAddRequest request) {
        if(!lambdaQuery().eq(User::getUsername,request.getUsername()).list().isEmpty()){
            throw new BusinessException(RespCode.CONFLICT,"用户名重复");
        }
        User user = BeanUtils.toBean(request, User.class,
            (source, target) -> {
                String salt = UUID.randomUUID().toString();
                target.setUserStatus(UserStatus.ACTIVE);
                target.setSalt(salt);
                target.digestPassword();
            }
        );

        this.save(user);
    }

    @Override
    public UserLoginVo login(UserLoginRequest request) {
        // 登录
        // 根据用户名获取用户
        List<User> userList = this.lambdaQuery( ).eq(User::getUsername, request.getUsername( )).list( );
        if(userList==null || userList.isEmpty()){
            throw new BusinessException(RespCode.PARAM_ERROR,"用户名或密码错误");
        }else{
            User user = userList.get(0);
            if(user.getUserStatus().equals(UserStatus.BAN)) throw new BusinessException(RespCode.NO_AUTH_ERROR,"用户被禁用");
            // 密码和盐拼接再计算md5
            String pwd = MD5.create( ).digestHex16(request.getPassword()+user.getSalt());
            if(!pwd.equals(user.getPassword())) throw new BusinessException(RespCode.PARAM_ERROR,"用户名或密码错误");
            return BeanUtils.toBean(
                user,UserLoginVo.class,
                (source, target) -> {
                    target.setToken(jwtTool.createToken(source.getId()));
                }
            );
        }
    }

    @Override
    public UserLoginVo register(UserRegisterRequest request) {
        if(!StrUtil.equals(request.getPassword(),request.getPassword2())){
            throw new BusinessException(RespCode.PARAM_ERROR,"两次输入密码不一致");
        }

        if(!lambdaQuery().eq(User::getUsername,request.getUsername()).list().isEmpty()){
            throw new BusinessException(RespCode.CONFLICT,"用户名重复");
        }


        User user = BeanUtils.toBean(request, User.class, (req, target) -> {
            String salt = UUID.randomUUID( ).toString( );
            target.setUserStatus(UserStatus.ACTIVE);
            target.setRole(UserRoles.USER);
            target.setSalt(salt);
            target.digestPassword( );
        });
        this.save(user);

        // 注册
        return BeanUtils.toBean(
            user,UserLoginVo.class,
            (source, target) -> {
                target.setToken(jwtTool.createToken(source.getId()));
            }
        );
    }
}




