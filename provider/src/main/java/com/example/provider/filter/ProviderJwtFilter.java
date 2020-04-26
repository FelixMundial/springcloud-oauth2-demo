package com.example.provider.filter;

import com.example.commons.domain.TblUser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yinfelix
 * @date 2020/4/24
 */
@Component
public class ProviderJwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            token = new String(Base64Utils.decodeFromString(token));

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(token);

            /*
            还原用户信息（除密码），最终以UsernamePasswordAuthenticationToken的形式放入上下文
             */
            String principal = jsonNode.get("principal").asText();
            TblUser userInfo = mapper.readValue(principal, TblUser.class);

            List<GrantedAuthority> authorities = new ArrayList<>();
            jsonNode.get("authorities").forEach(singleJsonNode -> {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(singleJsonNode.asText());
                authorities.add(simpleGrantedAuthority);
            });
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userInfo, null, authorities
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            /*
            将UsernamePasswordAuthenticationToken放入应用上下文
             */
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
