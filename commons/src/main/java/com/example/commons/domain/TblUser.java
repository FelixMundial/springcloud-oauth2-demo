package com.example.commons.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * tbl_user
 * @author 
 */
@Data
public class TblUser implements Serializable {
    private Long id;

    /**
     * 用户登录昵称

     */
    private String username;

    /**
     * 用户登录密码
     */
    @JsonIgnore
    private String password;

    /**
     * 用户注册邮箱
     */
    private String email;

    /**
     * 用户注册手机号
     */
    private String phone;

    /**
     * 备注
     */
    private String notes;

    private Date creationTime;

    private Date lastupdateTime;

    private static final long serialVersionUID = 1L;
}