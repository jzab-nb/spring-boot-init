package xyz.jzab.initDemo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.jzab.common.domain.BaseResponse;
import xyz.jzab.common.enums.RespCode;
import xyz.jzab.common.exception.BusinessException;
import xyz.jzab.initDemo.domain.dto.user.UserAddRequest;
import xyz.jzab.initDemo.domain.dto.user.UserUpdateRequest;
import xyz.jzab.initDemo.domain.po.User;
import xyz.jzab.initDemo.domain.query.UserPageQuery;
import xyz.jzab.initDemo.domain.vo.UserVo;
import xyz.jzab.initDemo.service.UserService;

/**
 * @author JZAB
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    // 使用final类型的变量和@RequiredArgsConstructor注解实现依赖注入
    private final UserService userService;

    @PostMapping
    public BaseResponse<Void> addUser(@RequestBody UserAddRequest userAddRequest){
        userService.addUser(userAddRequest);
        return BaseResponse.builder(RespCode.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteUser(@PathVariable String id){
        if(userService.getById(id)==null) throw new BusinessException(RespCode.NOT_FOUND,"要删除的用户不存在");
        userService.removeById(id);
        return BaseResponse.success().build();
    }

    // 局部更新 全量更新是put
    @PatchMapping("/{id}")
    public BaseResponse<Void> update(@PathVariable Long id, @RequestBody UserUpdateRequest request){
        if(userService.getById(id)==null) throw new BusinessException(RespCode.NOT_FOUND,"要更新的用户不存在");
        User user = BeanUtil.toBean(request, User.class);
        user.setId(id);
        user.digestPassword();
        userService.updateById(user);
        return BaseResponse.success( ).build();
    }


    // 获取单个用户的信息
    @GetMapping("/{id}")
    public BaseResponse<UserVo> getUser(@PathVariable Long id){
        User user = userService.getById(id);
        if(user==null) throw new BusinessException(RespCode.NOT_FOUND);
        // 通过Hutool方便的转换po到vo
        return BaseResponse
            .success( )
            .body(
                BeanUtil.toBean(user, UserVo.class)
            );
    }

    // 分页获取用户信息
    @PostMapping("/page")
    public BaseResponse<Page<User>> pageUser(@RequestBody UserPageQuery query){
        return BaseResponse.success(userService.page(query.toDefaultPageEntity()));
    }

    @PostMapping("/login")
    public BaseResponse<Void> login(){
        return BaseResponse.success( ).build( );
    }

    @PostMapping("/register")
    public BaseResponse<Void> register(){
        return BaseResponse.success( ).build( );
    }
}
