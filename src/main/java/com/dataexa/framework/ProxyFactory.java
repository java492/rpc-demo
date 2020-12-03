package com.dataexa.framework;

import com.dataexa.protocol.dubbo.NettyClient;
import com.dataexa.protocol.dubbo.NettyServer;
import com.dataexa.protocol.http.HttpClient;
import com.dataexa.register.RemoteRegister;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Random;

/**
 * @Author 胡志成
 * @Date 2020/11/6
 */
public class ProxyFactory {
    //jdk动态代理传递一个接口类
    public static <T> T getProxy(Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] {interfaceClass}, (proxy, method, args) -> {
            Protocol protocol = ProtocolFactory.getProtocol();
            //创建消息体
            Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
            List<URL> urlList = RemoteRegister.getUrlList(interfaceClass.getName());
            URL url = random(urlList);
            return protocol.send(url, invocation);
        });
    }

    /**
     * 随机获取url或机器
     * @param urls url集合
     * @return 一台机器的url
     */
    private static URL random(List<URL> urls) {
        Random random = new Random();
        int i = random.nextInt(urls.size());
        return urls.get(i);
    }
}
