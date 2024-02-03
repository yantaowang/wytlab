package com.wyt.wytlab;


import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class JSONtest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        System.out.println(JSONArray.toJSONString(list));
    }
}
