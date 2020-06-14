package com.atguigu.mynetty.protocol.request;

import com.atguigu.mynetty.protocol.Packet;
import com.atguigu.mynetty.protocol.command.Command;
import com.atguigu.mynetty.serialize.SerializerAlgorithm;
import com.atguigu.mynetty.serialize.impl.JSONSerializer;
import lombok.Data;

@Data
public class MessageRequestPacket extends Packet {

    private String toUserId;
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    @Override
    public Byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }
}
