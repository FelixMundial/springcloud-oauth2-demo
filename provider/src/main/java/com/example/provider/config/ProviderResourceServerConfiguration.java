package com.example.provider.config;

import com.example.provider.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @apiNote 对网关放行的用户token进行校验（服务进行认证之后的鉴权）
 * @author yinfelix
 * @date 2020/4/22
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@Configuration
public class ProviderResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "resource1";

    @Autowired
    private TokenStore customJwtTokenStore;

    @Autowired
    CustomAccessDeniedHandler accessDeniedHandler;

    /**
     * 客户端路由过滤
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**")
                .access("#oauth2.hasAnyScope('role_admin0', 'role_admin')")
                .anyRequest().authenticated()
                .and().csrf().disable();
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                .tokenStore(customJwtTokenStore)
                /*.stateless(true)*/;
    }

    /**
     * @deprecated 易造成授权服务器压力，故使用JWT令牌
     * 远程调用授权服务器对access_token进行检验
     */
//    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setCheckTokenEndpointUrl("");
        tokenServices.setClientId("client");
        tokenServices.setClientSecret("secret");
        return tokenServices;
    }
}
