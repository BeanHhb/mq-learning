package com.bean.communication.fullduplex;

import com.bean.communication.fullduplex.client.LiClient;
import com.bean.communication.fullduplex.server.ZhangServer;

/**
 * @Author Bean
 * @Date 2023/2/21 23:03
 */
public class Main {

    public static void main(String[] args) {

        new ZhangServer().start();
        new LiClient().start();
    }
}
