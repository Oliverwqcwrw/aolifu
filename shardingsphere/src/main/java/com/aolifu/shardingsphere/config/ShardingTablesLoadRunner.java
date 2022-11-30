package com.aolifu.shardingsphere.config;


import com.aolifu.shardingsphere.config.algorithm.CustomAlgorithm;
import com.aolifu.shardingsphere.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目启动后 读取已有分表 进行缓存
 */
@Slf4j
@Order(value = 1) // 数字越小 越先执行
@Component
public class ShardingTablesLoadRunner implements CommandLineRunner {

    @Resource
    private UserMapper userMapper;

    @Override
    public void run(String... args){
        CustomAlgorithm.setUserMapper(userMapper);

    }
}
