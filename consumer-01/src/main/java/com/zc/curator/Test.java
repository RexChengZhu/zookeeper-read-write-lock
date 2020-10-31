package com.zc.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.RetryOneTime;

import java.util.Arrays;
import java.util.concurrent.Executor;

public class Test {
    public static void main(String[] args) throws Exception{
        CuratorFramework client = CuratorFrameworkFactory.builder()
                // IP 地址 + 端口号，多个用逗号隔开
                .connectString("localhost:2181")
                // 会话超时时间
                .sessionTimeoutMs(2000)
                // 重连机制
                .retryPolicy(new RetryOneTime(1000))
                // 命名空间，用该客户端操作的东西都在该节点之下
                .namespace("kkk")

                .build();

        client.start();


        NodeCache nodeCache = new NodeCache(client, "/msm");

        nodeCache.start();

        nodeCache.getListenable().addListener(()->{
            System.out.println(nodeCache.getCurrentData().getPath());
            System.out.println(Arrays.toString(nodeCache.getCurrentData().getData()));
        });


        int i = System.in.read();
        System.out.println(i);





        client.close();
    }
}
