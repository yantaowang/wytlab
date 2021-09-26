package com.wyt.wytlab.dubbo;

import java.lang.reflect.InvocationTargetException;

public class WrapperTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Protocol protocol = new ProtocolFilterWrapper(new ProtocolListenerWrapper(new DubboProtocol()));
        protocol.export("wyt");

        ProtocolFilterWrapper dubboProtocol = ProtocolFilterWrapper.class.getConstructor(Protocol.class).newInstance(new DubboProtocol());
        System.out.println(11);

    }
}
