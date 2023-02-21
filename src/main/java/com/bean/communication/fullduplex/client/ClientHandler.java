package com.bean.communication.fullduplex.client;

import com.bean.communication.fullduplex.helper.TimeHelper;
import com.bean.communication.fullduplex.protocal.packet.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static com.bean.communication.halfduplex.common.MsgConstant.COUNT_LEVEL_3;

/**
 * @Author Bean
 * @Date 2023/2/21 22:50
 */
@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<Message> {

    public static final ClientHandler INSTANCE = new ClientHandler();

    private final String z0 = "吃了没，您吶?";
    private final String l1 = "刚吃。";
    private final String l2 = "您这，嘛去？";
    private final String z3 = "嗨！吃饱了溜溜弯儿。";
    private final String l4 = "有空家里坐坐啊。";
    private final String z5 = "回头去给老太太请安！";

    //worker只有1个线程，不需要原子类
    private int cnt1 = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        new Thread(() -> {
            //连接建立完成
            for (int i = 0; i < COUNT_LEVEL_3; i++) {
                ctx.write(new Message(l2));
                ctx.write(new Message(l4));
                if ((i + 1) % 100 == 0) {
                    ctx.flush();
                }
            }
        }).start();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("LiClient exception：" + cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        if (z0.equals(message.getMsg())) {
            cnt1++;
            channelHandlerContext.write(new Message(l1));
            if (cnt1 % 100 == 0) {
                channelHandlerContext.flush();
            }
            if (cnt1 == COUNT_LEVEL_3) {
                TimeHelper.end();
            }
        }
    }
}
