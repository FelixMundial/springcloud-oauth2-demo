package com.example.provider.controller;

import com.example.commons.domain.TblUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yinfelix
 * @date 2020/4/18
 */
@RequestMapping("/api")
@RestController
public class ProviderController {

    @GetMapping("/admin0")
    @PreAuthorize("hasAnyAuthority('role_admin0')")
    public String admin0() {
        TblUser userInfo = (TblUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userInfo.getUsername() + "访问资源admin0 (" + userInfo.getEmail() + ", " + userInfo.getId() + ")";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('role_admin0', 'role_admin')")
    public String admin() {
        TblUser userInfo = (TblUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userInfo.getUsername() + "访问资源admin (" + userInfo.getEmail() + ", " + userInfo.getId() + ")";
    }

    @GetMapping("/vip")
    public String vip() {
        TblUser userInfo = (TblUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userInfo.getUsername() + "访问资源vip (" + userInfo.getEmail() + ", " + userInfo.getId() + ")";
    }
}
