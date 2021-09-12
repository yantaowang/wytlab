package com.wyt.wytlab.dubbo;

public class ProtocolFilterWrapper implements Protocol{
    private Protocol protocol;

    public ProtocolFilterWrapper(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public void export(String name) {
        System.out.println("ProtocolFilterWrapper");
        protocol.export(name);
    }
}
