package com.example.uaa.service.impl;

import com.example.uaa.dao.TblRoleDao;
import com.example.commons.domain.TblRole;
import com.example.uaa.service.TblRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yinfelix
 * @date 2020/4/23
 */
@Service
public class TblRoleServiceImpl implements TblRoleService {
    @Resource
    private TblRoleDao roleDao;

    @Override
    public List<TblRole> selectByUserId(Long id) {
        return roleDao.selectByUserId(id);
    }
}
