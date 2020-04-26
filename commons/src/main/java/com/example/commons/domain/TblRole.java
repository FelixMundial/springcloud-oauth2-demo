package com.example.commons.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tbl_role
 * @author 
 */
@Data
public class TblRole implements Serializable {
    private Long id;

    /**
     * 父角色id
     */
    private Long parentId;

    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;

    /**
     * 备注
     */
    private String notes;

    private Date creationTime;

    private Date lastupdateTime;

    private static final long serialVersionUID = 1L;
}