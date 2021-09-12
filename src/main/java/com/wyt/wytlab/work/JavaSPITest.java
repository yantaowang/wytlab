package com.wyt.wytlab.work;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
import java.util.ServiceLoader;

public class JavaSPITest {
    public static void main(String[] args) throws IOException {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        serviceLoader.forEach(Robot::sayHello);

        System.out.println(Boolean.class);
        System.out.println(Boolean.TYPE);

        System.setProperty("key1", "123");
        System.out.println(System.getProperty("key1"));

        String content = "aa=bbc";
        Properties properties = new Properties();
        properties.load(new StringReader(content));
        System.out.println(properties.get("aa"));

        Person person = new Person(1);
        person.setAge(1);
        String cotent = JSON.toJSONString(person);
        Object obj = JSON.parseObject(cotent);

        Person p = Person.class.cast(obj);
    }
}
