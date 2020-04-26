package com.example.uaa.base.service;

import java.util.List;

/**
 * @author yinfelix
 * @date 2020/3/3
 */
public interface CommonsService<T> {
    T selectByPrimaryId(Integer id);

    T selectByName(String name);

    List<T> selectAll();
}
