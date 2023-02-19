package com.bean.communication;

import com.bean.communication.client.LiClient;
import com.bean.communication.common.MsgRepository;
import com.bean.communication.protocal.codec.PacketCodeC;
import com.bean.communication.protocal.packet.MsgPacket;
import com.bean.communication.server.ZhangServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Map;

import static com.bean.communication.client.LiClient.*;
import static com.bean.communication.common.MsgConstant.*;

/**
 * @Author ${USER}
 * @Date ${DATE} ${TIME}
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = ZhangServer.bootstrap();
        ChannelFuture serverChannelFuture = ZhangServer.bind(serverBootstrap, PORT);
        Bootstrap clientBootstrap = LiClient.bootstrap();
        ChannelFuture clientChannelFuture = LiClient.connect(clientBootstrap, HOST, PORT, MAX_RETRY);
        serverChannelFuture.await();
        clientChannelFuture.await();
        // sleep1s等接入
        Thread.sleep(1000);

        for (int i = 0; i < COUNT_LEVEL_3; i++) {
            MsgPacket one = MsgRepository.getInstance().getZhangMsgPacket(MSG_SESSION_ONE);
            MsgPacket two = MsgRepository.getInstance().getLiMsgPacket(MSG_SESSION_TWO);
            MsgPacket three = MsgRepository.getInstance().getLiMsgPacket(MSG_SESSION_THREE);
            sendMsg(ZhangServer.getChannel(HOST), one);
            sendMsg(clientChannelFuture.channel(), two);
            sendMsg(clientChannelFuture.channel(), three);
        }
    }

    private static void sendMsg(Channel channel, MsgPacket packet) {
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(packet);
        channel.writeAndFlush(byteBuf);
    }
}