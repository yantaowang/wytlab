package com.wyt.wytlab.dubbo;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {
    private static Class<Person> cls;

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "id");
        Person person = new Person(111);
        atomicIntegerFieldUpdater.compareAndSet(person, 111, 123);
        System.out.println(person.getId());

        test(Person.class);
    }

    public static void test(Class<?> cls) {
        String simple = cls.getSimpleName();
        System.out.println(simple.substring(0,2));

        Map<String, String> parameters = Collections.singletonMap("hello", ":*,/-._0123abcdABCD");
    }
}
