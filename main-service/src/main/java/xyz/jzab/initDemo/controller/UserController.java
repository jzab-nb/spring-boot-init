package xyz.jzab.initDemo.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.jzab.common.domain.BaseResponse;
import xyz.jzab.initDemo.domain.dto.user.UserAddRequest;
import xyz.jzab.initDemo.domain.po.User;
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
        return BaseResponse.success(null);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteUser(@PathVariable String id){
        userService.removeById(id);
        return BaseResponse.success().build();
    }

    @PutMapping("/{id}")
    public BaseResponse<Void> putUser(@PathVariable String id){
        return BaseResponse.success( ).build( );
    }

    @PatchMapping("/{id}")
    public BaseResponse<Void> patch(@PathVariable String id){
        return BaseResponse.success( ).build( );
    }


    @GetMapping("/{id}")
    public BaseResponse<UserVo> getUser(@PathVariable Long id){
        // 通过Hutool方便的转换po到vo
        return BaseResponse
            .success( )
            .body(
                BeanUtil.toBean(userService.getById(id), UserVo.class)
            );
    }

    @GetMapping("/page")
    public BaseResponse<Void> listUser(){
        return BaseResponse.success(null);
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
