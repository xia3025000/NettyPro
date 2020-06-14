package com.atguigu.mynetty.protocol;

import lombok.Data;

@Data
public abstract class Packet {

    //版本号,当前统一版本无用
    private Byte version;
    //指令
    public abstract Byte getCommand();
    //序列化算法号
    public abstract Byte getSerializerAlgorithm();

}
