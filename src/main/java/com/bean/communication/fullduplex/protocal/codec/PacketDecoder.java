package com.bean.communication.fullduplex.protocal.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 包解析器
 *
 * @Author Bean
 * @Date 2023/2/21 21:42
 */
public class PacketDecoder extends ByteToMessageDecoder {

    private static LengthDecoder ld = new LengthDecoder();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int len = byteBuf.readableBytes();
        if (len <= LengthDecoder.LENGTH_FIELD_LENGTH) {
            return;
        }

        Object decode = ld.decode(channelHandlerContext, byteBuf);
        if (decode != null) {
            list.add(decode);
        }
    }
}
