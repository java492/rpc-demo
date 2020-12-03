package com.dataexa.framework;

/**
 * @Author 胡志成
 * @Date 2020/11/12
 */
public interface Protocol {
    void start(URL url);
    String send(URL url, Invocation invocation);
}
