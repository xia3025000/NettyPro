package com.atguigu.mynetty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.mynetty.serialize.Serializer;
import com.atguigu.mynetty.serialize.SerializerAlgorithm;

/**
 * 一种序列化算法
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte gerSerializerAlgorithm() {
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
