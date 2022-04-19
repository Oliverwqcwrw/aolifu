package com.aolifu.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service(version = "1.0.0")
@Component
public class ProviderServiceImpl implements ProviderService {

    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}