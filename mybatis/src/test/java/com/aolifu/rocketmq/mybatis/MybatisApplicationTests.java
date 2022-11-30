package com.aolifu.rocketmq.mybatis;

import com.aolifu.mybatis.MybatisApplication;
import com.aolifu.mybatis.entity.EmpInfo;
import com.aolifu.mybatis.entity.Student;
import com.aolifu.mybatis.entity.User;
import com.aolifu.mybatis.mapper.EmpInfoMapper;
import com.aolifu.mybatis.mapper.StudentMapper;
import com.aolifu.mybatis.mapper.UserMapper;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest(classes = MybatisApplication.class)
class MybatisApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    EmpInfoMapper empInfoMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StudentMapper studentMapper;

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

    @Test
    void addOneStudent(){
        for (Integer count = 0; count < 10; count++) {
            Student student = new Student()
                .setStudentId(count + new Random().nextLong()) //如果自己不去设置id则系统会分配给一个id
                .setStudentName("Godfery"+count)
                .setStudentAge(count)
                .setStudentScore(98.5-count)
                .setStudentBirthday(new Date());
            studentMapper.save(student);
        }
    }


}

