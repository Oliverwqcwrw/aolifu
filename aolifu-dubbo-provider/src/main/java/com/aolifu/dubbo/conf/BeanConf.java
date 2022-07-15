package com.aolifu.dubbo.conf;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ConfigurationProperties(prefix = "alert.group")
@Configuration
@Data
public class BeanConf {
    
    private List<BeanTest> list;

}
