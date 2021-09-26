package com.wyt.wytlab.dubbo;

public class DubboProtocol implements Protocol {

    @Override
    public void export(String name) {
        System.out.println("dubboprotocol:" + name);
    }
}
