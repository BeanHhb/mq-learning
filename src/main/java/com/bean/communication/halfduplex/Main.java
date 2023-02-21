package com.bean.communication.halfduplex;

import com.bean.communication.halfduplex.client.LiClient;
import com.bean.communication.halfduplex.common.MsgRepository;
import com.bean.communication.halfduplex.protocal.codec.PacketCodeC;
import com.bean.communication.halfduplex.protocal.packet.MsgPacket;
import com.bean.communication.halfduplex.server.ZhangServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import static com.bean.communication.halfduplex.client.LiClient.*;
import static com.bean.communication.halfduplex.common.MsgConstant.*;

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