package com.aolifu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
public class MyController {

    @Resource
    private ThreadPoolExecutor dynamicThreadPoolExecutor;

    @RequestMapping(value = "/example1")
    public String test1(){
        for (int i = 0;i< 10;i++) {
            int temp = i;
            dynamicThreadPoolExecutor.execute( () -> {
                System.out.println(temp + "=====我执行了");
            });
        }
        return "hello world";
    }
}
