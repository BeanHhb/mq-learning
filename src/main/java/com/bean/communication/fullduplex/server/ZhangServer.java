package com.bean.communication.fullduplex.server;

import com.bean.communication.fullduplex.protocal.codec.MsgDecoder;
import com.bean.communication.fullduplex.protocal.codec.MsgEncoder;
import com.bean.communication.fullduplex.protocal.codec.PacketDecoder;
import com.bean.communication.fullduplex.helper.NamedThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Date;

/**
 * @Author Bean
 * @Date 2023/2/21 21:26
 */
public class ZhangServer {

    public void start() {
        int port = 18000;
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1, new NamedThreadFactory("blg-boss"));
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(1, new NamedThreadFactory("blg-worker"));
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(65535))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new PacketDecoder());
                        sc.pipeline().addLast(new MsgDecoder());
                        sc.pipeline().addLast(MsgEncoder.INSTANCE);
                        sc.pipeline().addLast(ServerHandler.INSTANCE);
                    }
                });
        bootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + "：端口[" + port + "]绑定成功！");
            } else {
                System.out.println("端口[" + port + "]绑定失败！");
            }
        });
    }
}
