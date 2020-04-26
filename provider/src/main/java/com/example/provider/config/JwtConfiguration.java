package com.example.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author yinfelix
 * @date 2020/4/22
 */
@Configuration
public class JwtConfiguration {
    /*
    暂时采用对称加密（应以端点形式配置在配置文件中）
     */
//    @Value("security.oauth2.resource.jwt.key-value")
    public static final String SIGNING_KEY = "uaa123";

    /*
    实现jwt与Authentication对象互相转换的TokenStore
     */
    @Primary
    @Bean
    public JwtTokenStore customJwtTokenStore() {
        /*
        JwtTokenStore未实现父类storeAccessToken()方法
         */
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /*
    真正实现jwt与Authentication对象的互相转换
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(SIGNING_KEY);
        return accessTokenConverter;
    }
}
