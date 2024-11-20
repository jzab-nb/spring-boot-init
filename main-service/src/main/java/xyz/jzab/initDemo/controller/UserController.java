package xyz.jzab.initDemo.controller;

import org.springframework.web.bind.annotation.*;
import xyz.jzab.common.domain.BaseResponse;

/**
 * @author JZAB
 */
@RestController
@RequestMapping("/user")
public class UserController {
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
    public BaseResponse<Void> getUser(@PathVariable String id){
        return BaseResponse.success( ).build( );
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
