package com.wyt.wytlab.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {
    public static void main(String[] args) {
        List<Dog> list = new ArrayList<>();

        Dog dog = new Dog();
        dog.setList(Arrays.asList("1","2"));
        dog.setName("dog1");

        Dog dog2 = new Dog();
        dog2.setList(Arrays.asList("1","2"));
        dog2.setName("dog1");

        Dog dog4 = new Dog();
        dog4.setList(Arrays.asList("1","2"));
        dog4.setName("dog1");

        Dog dog5 = new Dog();
        dog5.setList(Arrays.asList("1","2"));
        dog5.setName("dog1");
        list.add(dog);
        list.add(dog2);
        list.add(dog4);
        list.add(dog5);

        List<Dog> list1 = list.stream().skip(3).limit(10).collect(Collectors.toList());
    }
}
