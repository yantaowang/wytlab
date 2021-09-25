//package com.wyt.wytlab.work;
//
//import jdk.internal.org.objectweb.asm.Handle;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.*;
//import java.util.function.Consumer;
//import java.util.function.Function;
//import java.util.function.Predicate;
//
//public class Demo {
//    static ThreadPoolExecutor threadPoolExecutor =  new ThreadPoolExecutor(5, 5, 10,
//            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
//    public static void main(String[] args) throws Exception {
////        String value = excute(() -> handleEx(), 5);
////        System.out.println(value);
//
//        testConsumer(item -> System.out.println(item + "你好"));
//
//        Function<Integer,String> func = o -> {return "sss";};
//        System.out.println(func.apply(1));
//List<Long> list = new ArrayList<>();
//        Predicate<String> predicate = o -> {return o.length() == 3;};
//        System.out.println(predicate.test("fff"));
//        Map<String, String> map = new HashMap<String,String>();
//        map.put("1","123");
//        map.putIfAbsent("1", "fff");
//    }
//    public static void testConsumer(Consumer<String> consumer) {
//        consumer.accept("tt");
//    }
//    public static String handleEx() {
//       return "WYT";
//    }
//    protected static final <V> V excute(Callable<V> task, long timeout) throws Exception {
//        V value = null;
//        if(timeout < 2) {
//            value = task.call();
//        } else {
//           Future<V> future = threadPoolExecutor.submit(task);
//           value = future.get(timeout, TimeUnit.MILLISECONDS);
//        }
//        return value;
//    }
//}
