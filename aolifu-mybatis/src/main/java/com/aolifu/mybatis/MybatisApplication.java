package com.aolifu.mybatis;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
@MapperScan(basePackages = "com.aolifu.mybatis.mapper")
public class MybatisApplication  {
    

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
    
}
