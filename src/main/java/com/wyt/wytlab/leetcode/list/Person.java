package com.wyt.wytlab.leetcode.list;

import ognl.MemberAccess;

import java.lang.reflect.Member;
import java.util.Map;

public class Person implements MemberAccess {
    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object setup(Map map, Object o, Member member, String s) {
        return null;
    }

    @Override
    public void restore(Map map, Object o, Member member, String s, Object o1) {

    }

    @Override
    public boolean isAccessible(Map map, Object o, Member member, String s) {
        return false;
    }
}
