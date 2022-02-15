//package com.wytlab.web.controller;
//
//import com.wytlab.starter.service.MqServiceConfiguration;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//@RestController
//public class TestController {
//
//    @Resource
//    private MqServiceConfiguration mqServiceConfiguration;
//
//    @Value("${com.mq.address}")
//    private String address;
//
//    @GetMapping(value = "/excute")
//    public String excute() {
//        return mqServiceConfiguration.exucte() + "::" + address;
//    }
//}
