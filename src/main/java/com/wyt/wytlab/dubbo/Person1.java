package com.wyt.wytlab.dubbo;

public class Person1 extends AbstractPerson implements IPerson{
    @Override
    public void run() {
        System.out.println("person1");
    }

}
