package com.wyt.wytlab.java;

import cn.hutool.core.clone.CloneSupport;
import lombok.Data;

import java.util.List;

@Data
public class Dog extends CloneSupport<Dog> {
    private String name = "wangwang";
    private int age = 3;

    private List<String> list;
}