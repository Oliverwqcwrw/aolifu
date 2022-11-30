package com.aolifu.shardingsphere;

import com.aolifu.shardingsphere.entity.User;
import com.aolifu.shardingsphere.mapper.UserMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertUserTest(){
        for(int i = 0;i < 10;i++) {
            User user = new User();
            user.setId(i + 20);
            user.setUsername("Oliver" + System.currentTimeMillis());
            user.setSex("male");
            user.setCreateTime(LocalDateTime.now());
            userMapper.insert(user);
        }

    }

    @Test
    public void selectUserListTest() {
        final List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void dropTableTest() {
        String sql = "drop table user_1";
        userMapper.dropTable(sql);
    }
}
