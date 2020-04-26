package com.example.uaa.service.impl;

import com.example.uaa.dao.TblUserDao;
import com.example.commons.domain.TblUser;
import com.example.uaa.service.TblUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yinfelix
 * @date 2020/3/3
 */
@Service
public class TblUserServiceImpl implements TblUserService {
    @Resource
    private TblUserDao dao;

    @Override
    public TblUser selectByPrimaryId(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public TblUser selectByName(String username) {
        Example example = new Example(TblUser.class);
        example.createCriteria().andEqualTo("username", username);
        return dao.selectOneByExample(example);
    }

    @Override
    public List<TblUser> selectAll() {
        return dao.selectAll();
    }
}
