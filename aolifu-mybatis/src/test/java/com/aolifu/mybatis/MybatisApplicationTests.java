package com.aolifu.mybatis;

import com.aolifu.mybatis.entity.User;
import com.aolifu.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
        List<User> list = userMapper.list();
        System.out.println(list.size());
    }

}

