package com.futao.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * zookeeper 操作
 *
 * @author ft
 * @date 2021/8/3
 */
public class Zk {

    // private static final String CONNECTION_STRING = "127.0.0.1:2181";
    public static final String CONNECTION_STRING = "zk1.local:2181,zk2.local:2182,zk3.local:2183";
    private static ZooKeeper zkClient = null;

    /**
     * 初始化链接
     *
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        zkClient = new ZooKeeper(CONNECTION_STRING, 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("收到事件" + watchedEvent + "--------------------------------------");
                List<String> children = null;
                try {
                    // 获取节点列表
                    children = zkClient.getChildren("/", true);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
                for (String child : children) {
                    System.out.println(child);
                }
                System.out.println("--------------------------------------");
            }
        });
    }

    /**
     * 创建节点
     *
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void create() throws InterruptedException, KeeperException {
        String s = zkClient.create("/pig", "f".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(s);
    }

    /**
     * 获取节点列表
     *
     * @throws InterruptedException
     * @throws KeeperException
     * @throws IOException
     */
    @Test
    public void getChild() throws InterruptedException, KeeperException, IOException {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        System.in.read();
    }

    /**
     * 判断节点是否存在
     *
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void exist() throws InterruptedException, KeeperException {
        Stat stat = zkClient.exists("/pig", false);
        if (stat != null) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }
    }


    /**
     * 断开链接
     *
     * @throws InterruptedException
     */
    @After
    public void close() throws InterruptedException {
        zkClient.close();
    }
}
