package xyz.jzab.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * @author JZAB
 */
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityConfig {
    @Bean
    public PasswordEncoder passWordEncoder(){
        return new BCryptPasswordEncoder(  );
    }

    @Bean
    public KeyPair keyPair(JwtProperties properties){
        // 获取密钥工厂
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
            properties.getLocation(),
            properties.getPassword().toCharArray()
        );

        return keyStoreKeyFactory.getKeyPair(
            properties.getAlias(),
            properties.getPassword().toCharArray()
        );
    }
}