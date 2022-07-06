package com.aolifu.controller;

import com.aolifu.bean.AlertMsg;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebHookController {
    
    @PostMapping(value = "/webHook")
    public String webHook(@RequestBody List<AlertMsg> list) {
        System.out.println(list.toString());
        return "SUCCESS";
    }
    
}
