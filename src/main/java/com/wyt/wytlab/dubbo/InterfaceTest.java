package com.wyt.wytlab.dubbo;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public class InterfaceTest {
    public static void main(String[] args) {
        replierF(new WorldHandler());
        //3、初始化一个有结果无计算的CompletableFuture
        CompletableFuture<String> future = CompletableFuture.completedFuture(kf("123"));
        future.whenComplete((res,t) -> {
            System.out.println(res);
            System.out.println(t);
        });

        HashMap<Integer, String> map = new HashMap<>();
        String val = map.computeIfAbsent(1,k->"111");
        String val1 = map.computeIfAbsent(1,k->"222");

        AtomicBoolean stopped = new AtomicBoolean(false);
        stopped.compareAndSet(true, true);
    }
    public static void replierF(Replier<?> replier) {
        System.out.println(((Replier) replier).reply(new World("wyt")));
    }
    public static String kf(String param) {
        return "hello" + param;
    }
}
