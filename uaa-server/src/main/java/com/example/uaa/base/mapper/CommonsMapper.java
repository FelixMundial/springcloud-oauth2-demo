package com.example.uaa.base.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author yinfelix
 * @date 2020/3/3
 */
public interface CommonsMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
