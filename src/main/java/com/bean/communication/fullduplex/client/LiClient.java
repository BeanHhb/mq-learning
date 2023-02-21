package com.bean.communication.fullduplex.client;

import com.bean.communication.fullduplex.protocal.codec.MsgDecoder;
import com.bean.communication.fullduplex.protocal.codec.MsgEncoder;
import com.bean.communication.fullduplex.protocal.codec.PacketDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * @Author Bean
 * @Date 2023/2/21 22:39
 */
public class LiClient {

    public void start() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(1);

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new PacketDecoder());
                        sc.pipeline().addLast(new MsgDecoder());
                        sc.pipeline().addLast(MsgEncoder.INSTANCE);
                        sc.pipeline().addLast(ClientHandler.INSTANCE);
                    }
                });
        // 建立连接
        bootstrap.connect("127.0.0.1", 18000).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + "：连接成功！");
            } else {
                System.out.println("重试次数用完，放弃连接！");
            }
        });

    }
}
