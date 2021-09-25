package com.wytlab.starter.service;

public class MqServiceConfiguration {
    private String address;
    private String channel;

    public MqServiceConfiguration(String address, String channel) {
        this.address = address;
        this.channel = channel;
    }

    public String exucte() {
        return address + ":" + channel;
    }
}
