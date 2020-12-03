package com.dataexa.framework;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author 胡志成
 * @Date 2020/11/12
 */
public class ProtocolFactory {
    public static Protocol getProtocol() {
        ServiceLoader<Protocol> load = ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = load.iterator();
        return iterator.next();
    }
}
