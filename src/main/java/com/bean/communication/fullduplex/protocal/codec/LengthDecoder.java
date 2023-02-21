package com.bean.communication.fullduplex.protocal.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 长度解析器
 *
 * @Author Bean
 * @Date 2023/2/21 21:44
 */
public class LengthDecoder extends LengthFieldBasedFrameDecoder {

    public static final int MAX_FRAME_LENGTH = Integer.MAX_VALUE;
    public static final int LENGTH_FIELD_OFFSET = 7;
    public static final int LENGTH_FIELD_LENGTH = 4;

    public LengthDecoder() {
        super(MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        return super.decode(ctx, in);
    }
}
