package com.aolifu.shardingsphere;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class CommonTest {

    @Test
    public void replaceTest() {
        String oldStr = "user";
        String newStr = "user_30";
        String str = "create table user (id,username,password)";
        int beginIndex = str.indexOf(oldStr);
        int endIndex = beginIndex + oldStr.length();
        System.out.println(str.substring(beginIndex, endIndex));
        final String s = str.replaceFirst(oldStr, newStr);
        System.out.println(s);
    }

    @Test
    public void getPastSevenTableName() {
        String str = "user_";
        LocalDateTime time = LocalDateTime.now();
        int num = 7;
        List<String> list = new ArrayList<>();
        time = time.plusDays(-7);
        while (num > 0) {
            list.add(str + time.getDayOfMonth());
            num--;
            time = time.plusDays(-1);
        }
        System.out.println(list);
    }
}
