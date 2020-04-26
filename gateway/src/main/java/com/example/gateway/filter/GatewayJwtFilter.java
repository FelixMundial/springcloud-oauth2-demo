package com.example.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yinfelix
 * @date 2020/4/24
 */
@Slf4j
@Component
public class GatewayJwtFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();

//            List<String> authorities = new ArrayList<>();
//            userAuthentication.getAuthorities().forEach(grantedAuthority ->
//                    authorities.add(grantedAuthority.getAuthority()));
            List<String> authorities = userAuthentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

            OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
            Map<String, String> requestParameters = oAuth2Request.getRequestParameters();
            Map<String, Object> jsonizedToken = new HashMap<>(requestParameters);

            jsonizedToken.put("principal", userAuthentication.getName());
            jsonizedToken.put("authorities", authorities);

            ObjectMapper mapper = new ObjectMapper();
            try {
                requestContext.addZuulRequestHeader("token",
                        Base64Utils.encodeToString(mapper.writeValueAsString(jsonizedToken).getBytes()));
            } catch (JsonProcessingException e) {
                log.error("", e);
            }
        }
        return null;
    }
}
