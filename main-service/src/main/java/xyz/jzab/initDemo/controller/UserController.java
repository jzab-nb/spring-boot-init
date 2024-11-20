package xyz.jzab.initDemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.jzab.common.domain.BaseResponse;
import xyz.jzab.initDemo.domain.po.User;
import xyz.jzab.initDemo.service.UserService;

/**
 * @author JZAB
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    // 使用final类型的变量和@RequiredArgsConstructor注解实现依赖注入
    private final UserService userService;

    @PostMapping
    public BaseResponse<Void> addUser(){
        return BaseResponse.success( ).build( );
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteUser(@PathVariable String id){
        return BaseResponse.success( ).build( );
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
    public BaseResponse<User> getUser(@PathVariable Long id){
        return BaseResponse.success( ).body(userService.getById(id));
    }

    @GetMapping("/page")
    public BaseResponse<Void> listUser(){
        return BaseResponse.success( ).build( );
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
