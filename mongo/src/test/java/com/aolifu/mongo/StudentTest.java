package com.aolifu.mongo;

import com.aolifu.mongo.entity.Student;
import com.aolifu.mongo.mapper.StudentMapper;
import java.util.Date;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest(classes = MongoApplication.class)
public class StudentTest {

    @Autowired
    private StudentMapper studentMapper;

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
