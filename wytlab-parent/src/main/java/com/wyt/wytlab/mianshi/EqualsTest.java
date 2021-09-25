package com.wyt.wytlab.mianshi;

public class EqualsTest {
    public static void main(String[] args) throws InterruptedException {
        User user = new User();
        User user1 = user;
        user1.setAge(11);
        user.setAge(12);
        System.out.println(user.hashCode());
        System.out.println(user1.hashCode());
        System.out.println(user.equals(user1));
        System.out.println(user.getAge() + ":" + user1.getAge());

        f(user1);
        System.out.println(user1);

        User user2 = new User("aaa", 33);
        User user3 = new User("bbb", 44);
        g(user2, user3);
        System.out.println(user2);
        System.out.println(user3);

        System.out.println("**************************************");
        System.out.println("王浩迪");
        System.out.println("爱");
        System.out.println("妈妈");

        int k = 1;
        while (true) {
            k += 100;
            System.out.println(k);
            Thread.sleep(1000);
        }
    }

    public static void f(User user) {
        user.setAge(144);
    }

    public static void g(User user, User user1) {
        user = user1;
    }
}
