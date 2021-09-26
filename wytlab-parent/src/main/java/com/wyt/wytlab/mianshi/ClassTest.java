package com.wyt.wytlab.mianshi;

import com.wyt.wytlab.work.Person;

import java.util.TreeMap;

public class ClassTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        System.out.println(11);
        User user = testClass(User.class);
        user.setName("111");
        System.out.println(user);
        System.out.println("******************");

        Class t = getOneClass("com.wyt.wytlab.mianshi.User");
        User user1 = (User) t.newInstance();
        user1.setAge(11);
        System.out.println(user1);

        TreeMap<Person,String> treeMap = new TreeMap();
        treeMap.put(new Person(2),"1");
        treeMap.put(new Person(1), "1");

    }
    public static <T> T testClass(Class<T> type) {
        try {
            System.out.println(type.isAssignableFrom(User.class));
           return type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static <T> Class<? extends T> getOneClass(String str) {
        try {
            return (Class<T>) Class.forName(str, true, ClassTest.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
