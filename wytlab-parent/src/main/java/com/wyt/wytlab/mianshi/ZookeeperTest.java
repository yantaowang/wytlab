//package com.wyt.wytlab.mianshi;
//
//import java.util.concurrent.TimeUnit;
//
//public class ZookeeperTest {
//    private static InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");
//    public static void main(String[] args) {
//        //1、重试策略：初试时间为1s 重试3次
//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//        //2、通过工厂创建连接
//        CuratorFramework client = CuratorFrameworkFactory.newClient(address, retryPolicy);
//        //3、开启连接
//        client.start();
//        //4 分布式锁
//        final InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");
//        //读写锁
//        //InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(client, "/readwriter");
//
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
//
//        for (int i = 0; i < 5; i++) {
//            fixedThreadPool.submit(new Runnable() {
//                @Override
//                public void run() {
//                    boolean flag = false;
//                    try {
//                        //尝试获取锁，最多等待5秒
//                        flag = mutex.acquire(5, TimeUnit.SECONDS);
//                        Thread currentThread = Thread.currentThread();
//                        if(flag){
//                            System.out.println("线程"+currentThread.getId()+"获取锁成功");
//                        }else{
//                            System.out.println("线程"+currentThread.getId()+"获取锁失败");
//                        }
//                        //模拟业务逻辑，延时4秒
//                        Thread.sleep(4000);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally{
//                        if(flag){
//                            try {
//                                mutex.release();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//            });
//        }
//    }
//    //获得了锁
//    public static boolean acquire(long time, TimeUnit unit){
//        try {
//            return mutex.acquire(time,unit);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    //释放锁
//    public static void release(){
//        try {
//            mutex.release();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
