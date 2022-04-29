package com.aolifu.mybatis.mapper;


import com.aolifu.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
     List<User> list();

}
