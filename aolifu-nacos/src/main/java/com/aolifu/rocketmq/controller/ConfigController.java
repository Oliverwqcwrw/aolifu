package com.aolifu.rocketmq.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ConfigController {

    @NacosValue(value = "${useLocalCache}", autoRefreshed = true)
    private boolean useLocalCache;

    @NacosInjected
    private NamingService namingService;

    @GetMapping(value = "/getConfig")
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }

    @GetMapping(value = "/getService")
    @ResponseBody
    public List<Instance> getService(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }
}