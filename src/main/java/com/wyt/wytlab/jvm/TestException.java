package com.wyt.wytlab.jvm;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class TestException {
    public static void main(String[] args) {
//        stackOverFlowError();
        test1();
//        test2();
//        test3();
//        test4();
    }

    private static  void test4() {
        Integer i = 1;
        while (i > 400000) {
            new Thread(()->{
                System.out.println("a");
                try {
                    Thread.sleep(5 * 1000 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"a1").start();
            i++;
        }
    }

    private static void test3() {
        ByteBuffer.allocateDirect(5 * 1024 * 1024);
     }

    private static void test2() {
        int i = 1;
        List<String> list = new ArrayList<>();
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

    private static void test1() {
        byte[] bytes = new byte[8 * 1024 *1024];
    }

    private static void stackOverFlowError() {
        stackOverFlowError();
    }
}
