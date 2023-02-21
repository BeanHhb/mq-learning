package com.bean.communication.fullduplex.protocal.codec;

import com.bean.communication.fullduplex.protocal.packet.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * 消息编码器
 *
 * 通信协议
 * 魔数0x12345678(4字节)|版本号(1字节)|序列化算法(1字节)|指令(1字节)|数据长度(4字节)|数据(N字节)
 * @Author Bean
 * @Date 2023/2/21 22:13
 */
@ChannelHandler.Sharable
public class MsgEncoder extends MessageToByteEncoder<Message> {

    public static Byte MSG = 1;

    public static Byte VERSION = 1;

    public static Byte SERIALIZER_VER = 1;

    private static final int MAGIC_NUMBER = 0x12345678;

    public static final MsgEncoder INSTANCE = new MsgEncoder();

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        if (message == null) {
            throw new RuntimeException("encode msg null");
        }
        byte[] bytes = message.getMsg().getBytes(Charset.forName("UTF-8"));
        int len = bytes.length;
        // 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(VERSION);
        byteBuf.writeByte(SERIALIZER_VER);
        byteBuf.writeByte(MSG);
        byteBuf.writeInt(len);
        byteBuf.writeBytes(bytes);

    }
}
