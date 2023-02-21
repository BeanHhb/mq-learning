package com.bean.communication.halfduplex.protocal.packet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Bean
 * @Date 2023/2/18 9:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgPacket extends Packet {

    /**
     * 序号
     */
    private Integer no;

    /**
     * 会话ID
     */
    private Integer session;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 内容
     */
    private String content;

    @Override
    public Byte getCommand() {
        Byte MSG = 1;
        return MSG;
    }
}
