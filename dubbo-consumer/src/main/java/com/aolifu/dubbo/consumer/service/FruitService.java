package com.aolifu.dubbo.consumer.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.aolifu.dubbo.api.FruitApi;
import org.springframework.stereotype.Component;

@Service(version = "1.0.0")
@Component
public class FruitService implements FruitApi {
    
    @Override
    public String getFruit(String name) {
        return "Hello " + name;
    }
}
