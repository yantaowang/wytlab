package com.wyt.wytlab.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Week1Test extends ClassLoader {
    public static final byte DIGITAL_255 = (byte) 255;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = getClassBytes("D:\\java培训\\作业相关\\Hello.xlass");

            // 根据要求处理字节码
            byte[] deBytes = handleByte(bytes);

            // 使用新的字节数组定义类
            return defineClass(name, deBytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    /**
     * 根据自定义的要求处理字节码
     *
     * @param oldBytes 原来的字节数组
     * @return 放回处理后的数组
     */
    private byte[] handleByte(byte[] oldBytes) {
        byte[] newBytes = new byte[oldBytes.length];

        for (int i = 0; i < oldBytes.length; i++) {
            newBytes[i] = (byte) (DIGITAL_255 - oldBytes[i]);
        }
        return newBytes;
    }

    /**
     * 获取字节数组
     * @param filePath class文件路径
     * @return 字节数组
     * @throws Exception
     */
    private byte[] getClassBytes(String filePath) throws Exception {
        return Files.readAllBytes(Paths.get(filePath));
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //使用自定义类加载器加载类
        Week1Test myClassLoader = new Week1Test();
        Class<?> clazz = myClassLoader.loadClass("Hello");

        //实例化对象
        Object obj = clazz.newInstance();
        //获取声明的方法
        Method method = clazz.getDeclaredMethod("hello");
        //方法调用
        method.invoke(obj);

        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }
}
