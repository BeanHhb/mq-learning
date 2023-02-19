package com.bean.communication.protocal.packet;

import lombok.Data;

/**
 * @Author Bean
 * @Date 2023/2/18 9:16
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     *
     * @return
     */
    public abstract Byte getCommand();
}
