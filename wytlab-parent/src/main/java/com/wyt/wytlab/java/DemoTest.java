//package com.wyt.wytlab.java;
//
//import java.io.File;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Comparator;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArraySet;
//import java.util.concurrent.atomic.AtomicLong;
//
//public class DemoTest {
//    private static AtomicLong atomicLong = new AtomicLong(1);
//    private static Collection<Integer> collection = new CopyOnWriteArraySet<>();
//    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        // 枚举
////        System.out.println(SexyTypeEnum.getMap());
//        sort();
//        System.out.println(atomicLong.getAndIncrement());
//        System.out.println(atomicLong.getAndIncrement());
//        collection.add(1);
//        collection.add(3);
//        collection.add(55);
//        System.out.println("**************************");
//        System.out.println("下面执行的是原来的版本");
//        loadUser();
//        System.gc();
//        //通过File实现文件的替换从而实现一个自动化的热部署
//        File oldFile = new File("D:\\solution\\wytlab\\target\\classes\\com\\wyt\\wytlab\\java\\GirlFrend.class");
//        oldFile.delete();
//        File newFile = new File("E:\\data\\GirlFrend.class");
//
//        //实现新老class文件的替换
//        boolean isRenameTo = newFile.renameTo(oldFile);
//        if(!isRenameTo) {
//            System.out.println("热部署失败!");
//        }
//        else {
//            System.out.println("下面执行的是现在的版本");
//            loadUser();
//        }
//    }
//
//    // 通过反射机制创建对象
//    public static void loadUser() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
//            NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
//        MyClassLoader myLoader = new MyClassLoader();
//        Class<?> class1 = myLoader.findClass("com.wyt.wytlab.java.GirlFrend");
//        Object obj1 = class1.newInstance();
//        Method method = class1.getMethod("add");
//        method.invoke(obj1);
//        System.out.println(obj1.getClass());
//        System.out.println(obj1.getClass().getClassLoader());
//    }
//
//    public static void buysugger() {
//        final BigDecimal TEN_CENTS = new BigDecimal(".10");
//        int itemsBought = 0;
//        BigDecimal funds = new BigDecimal("1.00");
//        for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
//            funds = funds.subtract(price);
//            itemsBought++;
//        }
//        System.out.println(itemsBought + "items bought.");
//        System.out.println("Money left over: $" + funds);
//    }
//    public static void sort() {
//        List<String> cities = Arrays.asList(
//                "Milan",
//                "london",
//                "San Francisco",
//                "Tokyo",
//                "New Delhi"
//        );
//        System.out.println(cities);
////[Milan, london, San Francisco, Tokyo, New Delhi]
//        cities.sort(String.CASE_INSENSITIVE_ORDER);
//        System.out.println(cities);
////[london, Milan, New Delhi, San Francisco, Tokyo]
//        cities.sort(Comparator.naturalOrder());
//        System.out.println(cities);
//    }
//}
