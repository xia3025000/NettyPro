package com.atguigu.mynetty.protocol.response;

import com.atguigu.mynetty.protocol.Packet;
import com.atguigu.mynetty.protocol.command.Command;
import com.atguigu.mynetty.serialize.SerializerAlgorithm;
import lombok.Data;

import java.util.List;

@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }

    @Override
    public Byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }
}
