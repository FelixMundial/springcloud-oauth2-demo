package com.example.commons.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tbl_permission
 * @author 
 */
@Data
public class TblPermission implements Serializable {
    private Long id;

    /**
     * 父权限id
     */
    private Long parentId;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 资源路径
     */
    private String resourceUrl;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 备注
     */
    private String notes;

    private Date creationTime;

    private Date lastupdateTime;

    private static final long serialVersionUID = 1L;
}