package com.example.uaa.service;

import com.example.commons.domain.TblRole;

import java.util.List;

/**
 * @author yinfelix
 * @date 2020/3/3
 */
public interface TblRoleService {
    List<TblRole> selectByUserId(Long id);
}
