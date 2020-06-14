package com.atguigu.mynetty.protocol.response;

import com.atguigu.mynetty.protocol.Packet;
import com.atguigu.mynetty.protocol.command.Command;
import com.atguigu.mynetty.serialize.SerializerAlgorithm;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEART_RESPONSE;
    }

    @Override
    public Byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }
}
