package com.wyt.wytlab;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class ZookeeperTest {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)  // 会话超时时间
                .connectionTimeoutMs(5000) // 连接超时时间
                .retryPolicy(retryPolicy)
                .namespace("base") // 包含隔离名称
                .build();
        curatorFramework.start();

        System.out.println(ZooKeeper.States.CONNECTED);
        System.out.println(curatorFramework.getState());

//        lock(curatorFramework);
        sms(curatorFramework);
//        createPersistent(curatorFramework, "/demo1/persistent");
//        createPersistentSeq(curatorFramework, "/demo1/persistent_seq");
//
//        createEphemeral(curatorFramework, "/demo1/ephemeral");
//        createEphemeralSeq(curatorFramework, "/demo1/ephemeral_seq");
//        dataOps(curatorFramework, "/demo1/persistent");
//        createPersistent(curatorFramework, "/demo1/a/b/c");
//        deleteCascade(curatorFramework, "/demo1/a");

    }

    public static void sms(CuratorFramework curatorFramework) throws Exception {
        String lockPath = "/leader";
        LeaderLatch leaderLatch = new LeaderLatch(curatorFramework, lockPath);
        LeaderLatchListener listener = new LeaderLatchListener() {
            @Override
            public void isLeader() {
                System.out.println("i am master");
            }

            @Override
            public void notLeader() {
                System.out.println("i am salver");
            }
        };
        leaderLatch.addListener(listener);
        leaderLatch.start();
        leaderLatch.await();
    }

    public static void lock(CuratorFramework curatorFramework ) {
        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/locks");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("线程 " + Thread.currentThread().getId() + " 尝试抢占锁...");
                try {
                    interProcessMutex.acquire();
                    System.out.println("线程 " + Thread.currentThread().getId() + " 获取锁成功!");
                    Thread.sleep(RandomUtils.nextInt(1000, 2000));

                    interProcessMutex.release();
                    System.out.println("线程 " + Thread.currentThread().getId() + " 释放锁成功!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * 创建持久化节点
     *
     * @param curatorFramework
     * @throws Exception
     */
    private static void createPersistent(CuratorFramework curatorFramework, String path) throws Exception {
        Stat stat = curatorFramework.checkExists().forPath(path);
        if (Objects.isNull(stat)) {
            curatorFramework.create()
                    .creatingParentContainersIfNeeded() // 如果指定节点的父节点不存在，则会自动级联创建父节点
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(path, "=== CONTENT ===".getBytes());
            System.out.println(path + " create success!");
        } else {
            System.out.println(path + " is exists!");
        }
    }

    /**
     * 获取某个节点数据，设置某个节点数据
     *
     * @param curatorFramework
     * @param path
     * @throws Exception
     */
    private static void dataOps(CuratorFramework curatorFramework, String path) throws Exception {
        curatorFramework.setData().forPath(path, "=== CONTENT ===".getBytes());
        byte[] bytes = curatorFramework.getData().forPath(path);
        System.out.println("->->-> " + new String(bytes));
    }

    /**
     * 创建持久化顺序节点
     *
     * @param curatorFramework
     * @throws Exception
     */
    private static void createPersistentSeq(CuratorFramework curatorFramework, String path) throws Exception {
        for (int i = 0; i < 5; i++) {
            curatorFramework.create()
                    .creatingParentContainersIfNeeded() // 如果指定节点的父节点不存在，则会自动级联创建父节点
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(path + "/i-", "persistent seq data".getBytes());

        }

        List<String> children = curatorFramework.getChildren().forPath(path);
        Collections.sort(children);
        for (String child : children) {
            System.out.println("-> " + child);
        }
    }

    /**
     * 创建临时节点
     *
     * @param curatorFramework
     * @param path
     * @throws Exception
     */
    private static void createEphemeral(CuratorFramework curatorFramework, String path) throws Exception {
        curatorFramework.create()
                .creatingParentContainersIfNeeded() // 如果指定节点的父节点不存在，则会自动级联创建父节点
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "ephemeral data".getBytes());
    }

    /**
     * 创建临时顺序节点
     *
     * @param curatorFramework
     * @param path
     * @throws Exception
     */
    private static void createEphemeralSeq(CuratorFramework curatorFramework, String path) throws Exception {
        for (int i = 0; i < 5; i++) {
            curatorFramework.create()
                    .creatingParentContainersIfNeeded() // 如果指定节点的父节点不存在，则会自动级联创建父节点
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(path + "/i-", "ephemeral seq data".getBytes());
        }

        List<String> children = curatorFramework.getChildren().forPath(path);
        Collections.sort(children);
        for (String child : children) {
            System.out.println("EphemeralSeq -> " + child);
        }
    }

    /**
     * 删除节点
     *
     * @param curatorFramework
     * @param path
     * @throws Exception
     */
    private static void delete(CuratorFramework curatorFramework, String path) throws Exception {
        curatorFramework.delete().forPath(path);
    }

    /**
     * 级联删除子节点
     *
     * @param curatorFramework
     * @param path
     * @throws Exception
     */
    private static void deleteCascade(CuratorFramework curatorFramework, String path) throws Exception {
        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
    }
}
