package com.dataexa.protocol.http;

import com.dataexa.framework.Invocation;
import com.dataexa.framework.Protocol;
import com.dataexa.framework.URL;

/**
 * @Author 胡志成
 * @Date 2020/11/12
 */
public class HttpProtocol implements Protocol {
    @Override
    public void start(URL url) {
        new HttpService().start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        return new HttpClient().send(url.getHostname(), url.getPort(), invocation);
    }
}
