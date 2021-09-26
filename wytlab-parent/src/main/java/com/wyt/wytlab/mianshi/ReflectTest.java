package com.wyt.wytlab.mianshi;

public class ReflectTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        User user = new User("www", 33);
        Class aClass = user.getClass();
        System.out.println(aClass.getName());

        Object obj = aClass.newInstance();
        System.out.println(obj);

        System.out.println(User.class.newInstance());
        System.out.println(Class.forName("com.wyt.wytlab.mianshi.User").newInstance());

        int kk = 3333;
        System.out.println(kk);
    }
}
