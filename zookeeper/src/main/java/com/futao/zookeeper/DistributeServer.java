package com.futao.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author ft
 * @date 2021/8/3
 */
public class DistributeServer {
    public static final String CONNECTION_STRING = "zk1.local:2181,zk2.local.local:2182,zk3.local:2183";

    public static void main(String[] args) throws InterruptedException, KeeperException {
        ZooKeeper client = getClient();
        String host = "server-1";
        client.create("/servers-pig" + host, host.getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(host + "上线了");
    }


    private static ZooKeeper getClient() {
        try {
            return new ZooKeeper(CONNECTION_STRING, 2000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("事件:" + event);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
