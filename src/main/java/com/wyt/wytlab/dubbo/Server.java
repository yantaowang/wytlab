package com.wyt.wytlab.dubbo;

public interface Server {

    void start();

    void stop();

    void register(Class service,Class serviceImpl);

}