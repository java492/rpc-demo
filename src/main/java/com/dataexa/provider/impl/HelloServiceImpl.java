package com.dataexa.provider.impl;

import com.dataexa.provider.api.HelloService;

/**
 * @Author 胡志成
 * @Date 2020/11/6
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello-->" + name;
    }
}
