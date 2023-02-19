package com.bean.communication.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.bean.communication.serializer.Serializer;
import com.bean.communication.serializer.SerializerAlgorithm;

/**
 * @Author Bean
 * @Date 2023/2/19 10:13
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
