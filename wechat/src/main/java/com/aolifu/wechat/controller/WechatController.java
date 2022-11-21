package com.aolifu.wechat.controller;

import com.aolifu.wechat.util.WeixinCheckoutUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WechatController {

    @GetMapping("/wx")
    public String wx(String signature,String timestamp,String nonce,String echostr) {
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (signature != null && WeixinCheckoutUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }

        return "hello";
    }
}
