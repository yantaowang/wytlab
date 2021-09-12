package com.wyt.wytlab.dubbo;

import java.lang.reflect.InvocationHandler;

public interface ProxyFactory {
    <T> T getProxy(Object target, InvocationHandler handler) throws Exception;
}
