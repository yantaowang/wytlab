package com.wyt.wytlab.dubbo;


import java.lang.reflect.InvocationHandler;

public class JavassistProxyFactory implements ProxyFactory{
    public <T> T getProxy(Object target, InvocationHandler handler) throws Exception {
        return (T) ProxyGenerator.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass(), handler);
    }
}
