package com.bean.communication.halfduplex.serializer;

/**
 * @Author Bean
 * @Date 2023/2/18 9:45
 */
public interface Serializer {

    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * java对象转二进制
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
