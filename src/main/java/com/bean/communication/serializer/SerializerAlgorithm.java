package com.bean.communication.serializer;

/**
 * @Author Bean
 * @Date 2023/2/19 10:10
 */
public interface SerializerAlgorithm {

    /**
     * json序列化标识
     */
    byte JSON = 1;

    byte PROTO_STUFF = 2;

    byte KRYO = 3;
}
