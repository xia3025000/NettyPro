package com.atguigu.mynetty.serialize;

/**
 * 序列化接口
 */
public interface Serializer {

    /**
     * 序列化算法号
     */
    byte gerSerializerAlgorithm();

    /**
     * 序列化
     */
    byte[] serialize(Object object);

    /**
     * 反序列化
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
