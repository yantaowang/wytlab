package com.wyt.wytlab.java;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class HutoolTest {
    public static void main(String[] args) {
        String a = "2017-05-06";
        Date value = Convert.toDate(a);

        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        //当前日期字符串，格式：yyyy-MM-dd
        String today= DateUtil.today();

        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr, "yyyy-MM-dd");
    }
}
