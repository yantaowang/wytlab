package com.wyt.wytlab;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class ZookeeperLister {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("namespace-demo2")
                .build();

        client.start();
        System.out.println(client.getState());

        // PathChildCache 监听一个节点下子节点的创建、删除、修改事件
        addListenerWithChildNodeCache(client, "/demo2/children");

        // NodeCache 监听一个节点的修改和创建事件
        addListenerWithNodeCache(client, "/demo2/single");

        // TreeCache 监听所有节点的创建、删除、修改事件
        addListenerWithTreeCache(client, "/demo2/tree");

        System.in.read();
    }

    /**
     * 监听某个节点的修改和创建事件
     *
     * @param curatorFramework
     * @param path
     */
    private static void addListenerWithNodeCache(CuratorFramework curatorFramework, String path) throws Exception {
        curatorFramework.create().creatingParentContainersIfNeeded().forPath(path);
        NodeCache nodeCache = new NodeCache(curatorFramework, path, false);
        NodeCacheListener nodeCacheListener = () -> {
            ChildData currentData = nodeCache.getCurrentData();
            System.out.println("监听到节点事件: " + currentData.getPath() + " -> " + new String(currentData.getData()));
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }

    /**
     * 监听某个节点下子节点的创建、删除、修改事件
     *
     * @param curatorFramework
     * @param path
     * @throws Exception
     */
    private static void addListenerWithChildNodeCache(CuratorFramework curatorFramework, String path) throws Exception {
        curatorFramework.create().creatingParentContainersIfNeeded().forPath(path);
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);
        PathChildrenCacheListener pathChildrenCacheListener = (curatorFramework1, event) -> {
            PathChildrenCacheEvent.Type type = event.getType();
            switch (type) {
                case CHILD_ADDED:
                    System.out.println("A child was added to the path：" + event.getType());
                    break;
                case CHILD_UPDATED:
                    System.out.println("A child's data was changed：" + event.getType());
                    break;
                case CHILD_REMOVED:
                    System.out.println("A child was removed from the path：" + event.getType());
                    break;
                case CONNECTION_SUSPENDED:
                    System.out.println("CONNECTION_SUSPENDED：" + event.getType());
                    break;
                case CONNECTION_RECONNECTED:
                    System.out.println("CONNECTION_RECONNECTED：" + event.getType());
                    break;
                case CONNECTION_LOST:
                    System.out.println("CONNECTION_LOST：" + event.getType());
                    break;
                case INITIALIZED:
                    System.out.println("INITIALIZED：" + event.getType());
                    break;
                default:
                    break;
            }
        };

        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();
    }

    /**
     * 监听所有节点的创建、删除、修改事件
     *
     * @param curatorFramework
     * @param path
     * @throws Exception
     */
    private static void addListenerWithTreeCache(CuratorFramework curatorFramework, String path) throws Exception {
        curatorFramework.create().creatingParentContainersIfNeeded().forPath(path);
        TreeCache treeCache = new TreeCache(curatorFramework, path);
        TreeCacheListener treeCacheListener = (curatorFramework1, event) -> {
            System.out.println("监听到节点事件：" + event.getType() + " - " + event.getData().getPath());
        };

        treeCache.getListenable().addListener(treeCacheListener);
        treeCache.start();
    }
}
