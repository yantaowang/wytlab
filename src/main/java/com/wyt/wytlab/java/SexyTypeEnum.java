package com.wyt.wytlab.java;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum SexyTypeEnum {
    MAIL(1, "男"),
    FAMAIL(2, "女");
    private Integer key;
    private String name;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Map<Integer,String> getMap() {
        return Stream.of(values()).collect(Collectors.toMap(SexyTypeEnum::getKey,SexyTypeEnum::getName));
    }
}
