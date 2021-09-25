package com.wyt.wytlab.mianshi;

public class TestCondition {
    public static void main(String[] args) {
        ConditionThread conditionThread = new ConditionThread();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                conditionThread.print5();
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                conditionThread.print10();
            }
        },"BB").start();
    }
}
