package com.aolifu.shardingsphere;

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
}
