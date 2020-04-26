package com.example.uaa.dao;

import com.example.commons.domain.TblRole;
import com.example.uaa.base.mapper.CommonsMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblRoleDao extends CommonsMapper<TblRole> {
    List<TblRole> selectByUserId(@Param("id") Long id);
}