package com.wyt.wytlab.dubbo;

public class Person extends AbstractPerson implements IPerson {
    volatile int id;

    Integer age;

    public Person() {
    }

    public Person(int id) {
        this.id = id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    @Override
    public void run() {
        System.out.println(555);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }
}
