package com.wyt.wytlab.aviator;

import java.util.Scanner;

/**
 * AI核心代码，估值1个亿
 */
public class AiMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            str = sc.next();
            str = str.replace("吗", "");
            str = str.replace("?", "!");
            str = str.replace("? ", "! ");
            System.out.println(str);
        }
    }
}