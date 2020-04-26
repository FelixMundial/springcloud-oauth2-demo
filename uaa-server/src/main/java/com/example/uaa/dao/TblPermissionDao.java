package com.example.uaa.dao;

import com.example.commons.domain.TblPermission;
import com.example.uaa.base.mapper.CommonsMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblPermissionDao extends CommonsMapper<TblPermission> {
    List<TblPermission> selectByUserId(@Param("id") Long id);
}