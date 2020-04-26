package com.example.gateway.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @apiNote 对接入网关的请求进行过滤（网关只负责认证）
 * @author yinfelix
 * @date 2020/4/24
 */
@EnableOAuth2Sso
//网关不能标记为资源服务器
//@EnableResourceServer
@Configuration
@Order(value = 0)
public class GatewayResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * 网关只放行登录请求
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/uaa/login/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
