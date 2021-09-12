package com.wyt.wytlab.dubbo;

import java.net.InetSocketAddress;

public class ClientTest {
    public static void main(String[] args) throws ClassNotFoundException {

        HelloService hs=Client.getRemoteProxyObj(Class.forName("org.rpc.service.HelloService"), new InetSocketAddress("127.0.0.1", 9999));
        System.out.println(hs.sayHello("world"));

    }
}
