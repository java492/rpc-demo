package com.dataexa.provider;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 胡志成
 * @Date 2020/11/6
 */
public class LocalRegister {
    //保存接口名称和对应的实现类
    private static ConcurrentHashMap<String, Class<?>> LOCAL_REGISTER = new ConcurrentHashMap<>();

    /**
     * 本地注册
     */
    public static void register(String interfaceName, Class<?> implClass) {
        LOCAL_REGISTER.putIfAbsent(interfaceName, implClass);
    }

    /**
     * 根据接口名称获取实现类
     */
    public static Class<?> getImplClass(String interfaceName) {
        return LOCAL_REGISTER.get(interfaceName);
    }
}
