package com.atguigu.mynetty.protocol.request;

import com.atguigu.mynetty.protocol.Packet;
import com.atguigu.mynetty.protocol.command.Command;
import com.atguigu.mynetty.serialize.SerializerAlgorithm;
import lombok.Data;

@Data
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;
    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }

    @Override
    public Byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }
}
