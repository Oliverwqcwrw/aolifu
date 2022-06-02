package com.aolifu.rocketmq.mybatis;

import com.aolifu.mybatis.entity.EmpInfo;
import com.aolifu.mybatis.entity.User;
import com.aolifu.mybatis.mapper.EmpInfoMapper;
import com.aolifu.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    EmpInfoMapper empInfoMapper;

    @Test
    public void contextLoads() {
        List<User> list = userMapper.list();
        System.out.println(list.size());
    }

    @Test
    public  void queryAllEmpById() {

        List<EmpInfo> empInfo = empInfoMapper.queryAllEmpins();
        System.out.println(empInfo);
    }


}

