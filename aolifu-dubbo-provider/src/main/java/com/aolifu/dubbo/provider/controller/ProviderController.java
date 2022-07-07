package com.aolifu.dubbo.provider.controller;

import com.aolifu.dubbo.api.FruitApi;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    
    @Reference(version = "1.0.0",check = false)
    FruitApi fruitApi;
    
    @GetMapping(value = "/getFruit/{name}")
    public String getFruit(@PathVariable String name) {
        final String apple = fruitApi.getFruit("apple");
        return apple;
    }
    
}
