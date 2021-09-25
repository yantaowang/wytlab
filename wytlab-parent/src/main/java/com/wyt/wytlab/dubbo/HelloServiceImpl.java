package com.wyt.wytlab.dubbo;


public class HelloServiceImpl implements HelloService {

    public Object sayHello(String name) {
        // TODO Auto-generated method stub
        return "hello，"+name;
    }

}
