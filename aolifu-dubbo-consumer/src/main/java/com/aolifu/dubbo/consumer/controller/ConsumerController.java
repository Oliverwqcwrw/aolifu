package com.aolifu.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aolifu.dubbo.api.ProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ConsumerController {
 
    // 使用兼容注入，可以使用dubbo原生注解@Reference注入
    @Reference(version = "1.0.0", check = false)
    ProviderService service;
 
    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable String name){
        return service.sayHello(name);
    }

    @GetMapping(value = "/")
    public String hello2(){
        return "Hello World";
    }
}