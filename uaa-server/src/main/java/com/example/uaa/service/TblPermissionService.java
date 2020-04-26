package com.example.uaa.service;

import com.example.uaa.base.service.CommonsService;
import com.example.commons.domain.TblPermission;

import java.util.List;

/**
 * @author yinfelix
 * @date 2020/3/3
 */
public interface TblPermissionService extends CommonsService<TblPermission> {
    List<TblPermission> selectByUserId(Long id);
}
