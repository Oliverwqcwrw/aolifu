package com.aolifu.dubbo.provider.controller;

import cn.hippo4j.adapter.base.ThreadPoolAdapter;
import cn.hippo4j.adapter.base.ThreadPoolAdapterState;
import cn.hippo4j.common.web.base.Result;
import cn.hippo4j.common.web.base.Results;
import cn.hippo4j.core.toolkit.IdentifyUtil;
import com.alibaba.fastjson.JSON;
import com.aolifu.dubbo.api.FruitApi;
import com.aolifu.dubbo.conf.BeanConf;
import com.aolifu.dubbo.conf.BeanTest;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static cn.hippo4j.adapter.base.ThreadPoolAdapterBeanContainer.THREAD_POOL_ADAPTER_BEAN_CONTAINER;

@RestController
public class ProviderController {
    
    @Reference(version = "1.0.0",check = false)
    FruitApi fruitApi;
    
    @Autowired
    ConfigurableEnvironment environment;
    
    @Autowired
    BeanConf beanConf;
    
    @GetMapping(value = "/getFruit/{name}")
    public String getFruit(@PathVariable String name) {
        final String apple = fruitApi.getFruit(name);
        return apple;
    }
    
    @GetMapping("/adapter/thread-pool/info")
    public Result<ThreadPoolAdapterState> getAdapterThreadPool() {
        ThreadPoolAdapter threadPoolAdapter = THREAD_POOL_ADAPTER_BEAN_CONTAINER.get("Dubbo");
        ThreadPoolAdapterState result = Optional.ofNullable(threadPoolAdapter).map(each -> {
            ThreadPoolAdapterState threadPoolState = each.getThreadPoolState("20880");
            threadPoolState.setIdentify(IdentifyUtil.getIdentify());
            return threadPoolState;
        }).orElse(null);
        return Results.success(result);
    }
    
    @GetMapping("/getList")
    public String getMap() {
        String name = "Legion";
        final List<BeanTest> list = beanConf.getList();
        final BeanTest beanTest = list.stream().filter(item -> item.getKey().equals(name)).findFirst().orElse(null);
        final String value = beanTest.getValue();
        return JSON.toJSONString(list);
    }
}
