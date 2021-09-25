package com.wyt.wytlab.mianshi;

public class StaticTest {
//     abstract class Human {}
//     class Man extends Human{}

    public void sayHello(Human human) {
        System.out.println("human");
    }

    public void sayHello(Man man) {
        System.out.println("man");
    }

    public static void main(String[] args) {
        Human human = new Man();

        StaticTest staticTest = new StaticTest();
        staticTest.sayHello(human);
    }
}
