package com.futao.zookeeper.dynamic;

import lombok.SneakyThrows;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 客户端监听服务实例的变化
 *
 * @author ft
 * @date 2021/8/3
 */
public class Client {
    private static ZooKeeper Zk = null;

    public static void main(String[] args) throws IOException, InterruptedException {
        Zk = new ZooKeeper("zk1.local:2181,zk2.local:2182,zk3.local:2183", 2000, new Watcher() {
            @SneakyThrows
            @Override
            public void process(WatchedEvent event) {
                // 可用的实例列表
                List<String> serverList = new ArrayList<>();
                List<String> children = Zk.getChildren("/server-pig", true);
                for (String child : children) {
                    byte[] data = Zk.getData("/server-pig/" + child, false, null);
                    // 拿到的是节点名，而不是data，why?
                    serverList.add(new String(data));
                }
                System.out.println(children);
            }
        });
        // 阻塞主线程
        TimeUnit.HOURS.sleep(1);
    }
}
