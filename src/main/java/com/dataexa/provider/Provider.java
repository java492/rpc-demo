package com.dataexa.provider;

import com.dataexa.framework.Protocol;
import com.dataexa.framework.ProtocolFactory;
import com.dataexa.framework.URL;
import com.dataexa.protocol.dubbo.NettyServer;
import com.dataexa.protocol.http.HttpService;
import com.dataexa.provider.api.HelloService;
import com.dataexa.provider.impl.HelloServiceImpl;
import com.dataexa.register.RemoteRegister;

/**
 * @Author 胡志成
 * @Date 2020/11/6
 */
public class Provider {
    public static void main(String[] args) {
        //1.服务本地注册--服务具体实现注册到本地,具体调用
        //{接口名称:对应的实现类}
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
        //2.远程注册--服务注册到注册中心提供给消费者调用
        //{接口名称:List<远程调用url>}
        URL url = new URL("localhost", 8080);
        RemoteRegister.register(HelloService.class.getName(), url);
        //3.暴露服务--启动服务
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }

}
