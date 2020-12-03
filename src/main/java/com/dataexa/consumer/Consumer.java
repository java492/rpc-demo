package com.dataexa.consumer;

import com.dataexa.framework.ProxyFactory;
import com.dataexa.provider.api.HelloService;

/**
 * @Author 胡志成
 * @Date 2020/11/6
 */
public class Consumer {
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        System.out.println(helloService.sayHello("hzc"));
    }
}
