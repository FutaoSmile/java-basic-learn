package com.futao.zookeeper.dynamic;

import com.futao.zookeeper.Zk;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author ft
 * @date 2021/8/3
 */
public class Server {
    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper(Zk.CONNECTION_STRING, 2000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("事件:" + event);
            }
        });
        // 节点
        String serverName = "/server-pig/server-3";
        // 创建的是非持久化的且带序列号的节点
        zooKeeper.create(serverName, "服务器3".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(serverName + " 创建成功");
        //保持在线状态
        TimeUnit.HOURS.sleep(1);
    }
}
