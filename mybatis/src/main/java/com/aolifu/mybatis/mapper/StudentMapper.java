package com.aolifu.mybatis.mapper;

import com.aolifu.mybatis.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/*
* dao层写法一
* 这里的用法其实和SpringDataJPA相似, 可根据需要来自定义方法
*
* 这种写法不需要写实现类
*
* MongoRepository<行对应的对象类型, 主键列类型>
* */
public interface StudentMapper extends MongoRepository<Student, String> {
    
    Student getAllByStudentName(String studentName);
        
}


