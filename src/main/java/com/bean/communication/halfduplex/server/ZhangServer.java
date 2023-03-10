package com.bean.communication.halfduplex.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Bean
 * @Date 2023/2/17 23:19
 */
public class ZhangServer {

    private static final Map<String, Channel> CHANNEL_MAPPING = new ConcurrentHashMap<>();

    private static final int PORT = 18000;

    public static void main(String[] args) {
        ServerBootstrap bootstrap = bootstrap();
        bind(bootstrap, PORT);
    }

    public static ServerBootstrap bootstrap() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7 ,4));
                        socketChannel.pipeline().addLast(new ServerHandler());
                    }
                });
        return bootstrap;
    }

    public static ChannelFuture bind(final ServerBootstrap serverBootstrap, final int port) {
        return serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + "?????????[" + port + "]???????????????");
            } else {
                System.out.println("??????[" + port + "]???????????????");
            }
        });
    }

    public static void putChannel(String host, Channel channel) {
        CHANNEL_MAPPING.put(host,channel);
    }

    public static Channel getChannel(String host) {
        return CHANNEL_MAPPING.get(host);
    }
}
