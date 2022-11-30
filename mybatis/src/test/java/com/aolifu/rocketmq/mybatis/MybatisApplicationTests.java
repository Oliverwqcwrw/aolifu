package com.aolifu.rocketmq.mybatis;

import com.aolifu.mybatis.MybatisApplication;
import com.aolifu.mybatis.entity.EmpInfo;
import com.aolifu.mybatis.entity.User;
import com.aolifu.mybatis.mapper.EmpInfoMapper;
import com.aolifu.mybatis.mapper.UserMapper;
import java.util.List;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MybatisApplication.class)
class MybatisApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    EmpInfoMapper empInfoMapper;



    @Test
    @Ignore
    public void contextLoads() {
        List<User> list = userMapper.list();
        System.out.println(list.size());
    }

    @Test
    @Ignore
    public  void queryAllEmpById() {

        List<EmpInfo> empInfo = empInfoMapper.queryAllEmpins();
        System.out.println(empInfo);
    }
}

