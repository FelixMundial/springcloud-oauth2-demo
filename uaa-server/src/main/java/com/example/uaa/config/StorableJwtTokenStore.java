package com.example.uaa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Date;
import java.util.Set;

/**
 * @author yinfelix
 * @date 2020/4/26
 */
@Slf4j
public class StorableJwtTokenStore extends JwtTokenStore {
    /**
     * Create a JwtTokenStore with this token enhancer (should be shared with the DefaultTokenServices if used).
     *
     * @param jwtTokenEnhancer
     */
    public StorableJwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer) {
        super(jwtTokenEnhancer);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        String value = token.getValue();
        Date expiration = token.getExpiration();
        Set<String> scope = token.getScope();
        Authentication userAuthentication = authentication.getUserAuthentication();
        Object principal = userAuthentication.getPrincipal();
//        AuthServerToken authServerToken = new AuthServerToken();
//        authServerToken.setToken(value);
//        authServerToken.setScope(scope.toString());
//        authServerToken.setExpireTime(expiration);
//
//        if (principal instanceof UserVo) {
//            authServerToken.setUserId(Math.toIntExact(((UserVo) principal).getId()));
//        }
//        authServerToken.setRefreshToken(token.getRefreshToken().getValue());
//        authServerToken.setCreateTime(new Date());
//        authServerTokenService.save(authServerToken);
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        log.debug("storeRefreshToken(): {}, {}", refreshToken, authentication);
    }
}
