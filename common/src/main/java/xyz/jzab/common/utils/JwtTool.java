package xyz.jzab.common.utils;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import org.springframework.stereotype.Component;
import xyz.jzab.common.enums.RespCode;
import xyz.jzab.common.exception.BusinessException;
import xyz.jzab.common.config.JwtProperties;

import java.security.KeyPair;
import java.time.Duration;
import java.util.Date;

/**
 * @author JZAB
 */
@Component
public class JwtTool {
    private final JWTSigner jwtSigner;
    private final JwtProperties properties;

    public JwtTool(KeyPair keyPair, JwtProperties properties){
        this.properties = properties;
        this.jwtSigner = JWTSignerUtil.createSigner("rs256",keyPair);
    }

    public String createToken(Long userId){
        return JWT.create()
                .setPayload("user",userId)
                .setExpiresAt(new Date(System.currentTimeMillis()+properties.getTokenTTL().toMillis()))
                .setSigner(jwtSigner)
                .sign();
    }

    public String createToken(Long userId, Duration ttl){
        return JWT.create()
                .setPayload("user",userId)
                .setExpiresAt(new Date(System.currentTimeMillis()+ttl.toMillis()))
                .setSigner(jwtSigner)
                .sign();
    }

    public Long parseToken(String token) {
        // 1.校验token是否为空
        if (token == null) {
            throw new BusinessException(RespCode.UNAUTHORIZED,"未登录");
        }
        // 2.校验并解析jwt
        JWT jwt;
        try {
            jwt = JWT.of(token).setSigner(jwtSigner);
        } catch (Exception e) {
            throw new BusinessException(RespCode.UNAUTHORIZED,"无效的token: "+e.getMessage());
        }
        // 2.校验jwt是否有效
        if (!jwt.verify()) {
            // 验证失败
            throw new BusinessException(RespCode.UNAUTHORIZED,"无效的token");
        }
        // 3.校验是否过期
        try {
            JWTValidator.of(jwt).validateDate();
        } catch (ValidateException e) {
            throw new BusinessException(RespCode.UNAUTHORIZED,"token已经过期");
        }
        // 4.数据格式校验
        Object userPayload = jwt.getPayload("user");
        if (userPayload == null) {
            // 数据为空
            throw new BusinessException(RespCode.UNAUTHORIZED,"无效的token");
        }

        // 5.数据解析
        try {
            return Long.valueOf(userPayload.toString());
        } catch (RuntimeException e) {
            // 数据格式有误
            throw new BusinessException(RespCode.UNAUTHORIZED,"无效的token");
        }
    }
}