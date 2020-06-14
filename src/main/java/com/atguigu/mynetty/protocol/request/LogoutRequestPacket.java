package com.atguigu.mynetty.protocol.request;

import com.atguigu.mynetty.client.console.ConsoleCommand;
import com.atguigu.mynetty.protocol.Packet;
import com.atguigu.mynetty.protocol.command.Command;
import com.atguigu.mynetty.serialize.SerializerAlgorithm;
import lombok.Data;

@Data
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }

    @Override
    public Byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }
}
