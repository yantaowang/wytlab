package com.wyt.wytlab.dubbo;

public class ProtocolListenerWrapper implements Protocol{

    private Protocol protocol;

    public ProtocolListenerWrapper(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public void export(String name) {
        System.out.println("protocolListenerWrapper:" + name);
        protocol.export(name);
    }
}
