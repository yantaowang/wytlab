package com.wyt.wytlab.dubbo;

public class ServerTest {

    public static void main(String[] args) {

        Server server=new ServerCenter(9999);
        server.register(HelloService.class,HelloServiceImpl.class);
        server.start();

    }

}
