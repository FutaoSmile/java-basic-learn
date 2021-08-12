package com.futao.zookeeper.book;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author ft
 * @date 2021/8/6
 */
public class S2 {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 2000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event);
            }
        });
        // 临时节点
        String s = zooKeeper.create("/path-futao/ep-node", "临时节点".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        // 临时节点下还可以创建节点吗
        // String s1 = zooKeeper.create("/path-futao/ep-node/node", "临时节点下的节点".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        TimeUnit.HOURS.sleep(1);

    }
}
