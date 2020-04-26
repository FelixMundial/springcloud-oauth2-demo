package com.example.uaa.service.impl;

import com.example.commons.domain.TblPermission;
import com.example.commons.domain.TblRole;
import com.example.commons.domain.TblUser;
import com.example.uaa.service.TblRoleService;
import com.example.uaa.service.TblUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yinfelix
 * @date 2020/3/8
 */
@Slf4j
@Service(BeanIds.USER_DETAILS_SERVICE)
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private TblUserService userService;
    @Autowired
    private TblRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TblUser user = userService.selectByName(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (user != null) {
            List<TblRole> roles = roleService.selectByUserId(user.getId());
            roles.forEach(role -> {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getCode());
                grantedAuthorities.add(simpleGrantedAuthority);
            });

            ObjectMapper mapper = new ObjectMapper();
            try {
                /*
                将密码外其他用户信息封装进principal字段中
                 */
                String principal = mapper.writeValueAsString(user);
                return new User(principal, user.getPassword(), grantedAuthorities);
            } catch (JsonProcessingException e) {
                log.error("", e);
            }
        }

        return null;
    }
}
