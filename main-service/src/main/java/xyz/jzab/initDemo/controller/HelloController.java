package xyz.jzab.initDemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jzab.common.domain.BaseResponse;
import xyz.jzab.common.enums.RespCode;

/**
 * @author JZAB
 */
@RestController
@RequestMapping("/hi")
public class HelloController {
    @GetMapping
    public BaseResponse<Void> hi(){
        return BaseResponse.builder(RespCode.NOT_FOUND).build();
    }
}
