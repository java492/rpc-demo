package com.dataexa.protocol.dubbo;

import com.dataexa.framework.Invocation;
import com.dataexa.framework.Protocol;
import com.dataexa.framework.URL;

/**
 * @Author 胡志成
 * @Date 2020/11/12
 */
public class DubboProtocol implements Protocol {
    @Override
    public void start(URL url) {
        new NettyServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        return new NettyClient().send(url.getHostname(), url.getPort(), invocation);
    }
}
