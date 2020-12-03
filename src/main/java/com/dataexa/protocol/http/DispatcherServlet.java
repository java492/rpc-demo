package com.dataexa.protocol.http;

import com.dataexa.framework.Invocation;
import com.dataexa.provider.LocalRegister;
import org.apache.commons.io.IOUtils;
import sun.nio.ch.IOUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author 胡志成
 * @Date 2020/11/6
 */
public class DispatcherServlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) {
        try {
            //具体处理
            ServletInputStream inputStream = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            //读到一个Invocation对象
            Invocation invocation = (Invocation) ois.readObject();
            //获取invocation中的接口名称
            String interfaceName = invocation.getInterfaceName();
            //根据接口名称从本地获取对应的实现类
            Class<?> implClass = LocalRegister.getImplClass(interfaceName);
            //获取实现类方法
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            //调用方法
            String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());
            //返回结果
            IOUtils.write(result, res.getOutputStream());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
    }
}
