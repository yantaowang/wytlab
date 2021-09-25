package com.wyt.wytlab.java;

import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 文件名称
            String fileName = "D:/java培训/作业相关/Hello.xlass";
            // 读取文件流
            InputStream is = this.getClass().getResourceAsStream(fileName);
            // 读取字节
            byte[] b = new byte[is.available()];
            is.read(b);
            //数据给JVM识别Class对象
            return defineClass(name,b, 0, b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }
}
