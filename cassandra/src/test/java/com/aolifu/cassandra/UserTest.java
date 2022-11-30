package com.aolifu.cassandra;

import com.aolifu.cassandra.entity.User;
import com.aolifu.cassandra.mapper.UserRecordRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CassandraApplication.class)
public class UserTest {

    @Autowired
    UserRecordRepository userRecordRepository;


    @Test
    public void cassandraInsertDataTest() {
        List<User> list = new ArrayList<>();
        for (int i = 2; i < 100; i++) {
            User testUser = new User();
            testUser.setId(i);
            testUser.setUsername("zhang"+i);
            testUser.setPassword("123456");
            list.add(testUser);
        }
        userRecordRepository.saveAll(list);
    }
}
