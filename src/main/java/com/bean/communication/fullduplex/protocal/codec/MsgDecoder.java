package com.bean.communication.fullduplex.protocal.codec;

import com.bean.communication.fullduplex.protocal.packet.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 消息解析器
 *
 * 通信协议
 * 魔数0x12345678(4字节)|版本号(1字节)|序列化算法(1字节)|指令(1字节)|数据长度(4字节)|数据(N字节)
 * @Author Bean
 * @Date 2023/2/21 21:58
 */
public class MsgDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Message message = new Message(new String(bytes, Charset.forName("UTF-8")));
        list.add(message);
    }
}
