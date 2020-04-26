package com.example.uaa.service.impl;

import com.example.uaa.dao.TblPermissionDao;
import com.example.commons.domain.TblPermission;
import com.example.uaa.service.TblPermissionService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yinfelix
 * @date 2020/3/3
 */
@Service
public class TblPermissionServiceImpl implements TblPermissionService {
    @Resource
    private TblPermissionDao dao;

    @Override
    public List<TblPermission> selectByUserId(Long id) {
        return dao.selectByUserId(id);
    }

    @Override
    public TblPermission selectByPrimaryId(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public TblPermission selectByName(String name) {
        Example example = new Example(TblPermission.class);
        example.createCriteria().andEqualTo("username", name);
        return dao.selectOneByExample(example);
    }

    @Override
    public List<TblPermission> selectAll() {
        return dao.selectAll();
    }


}
