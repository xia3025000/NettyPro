package com.atguigu.mynetty.client.console;

import com.atguigu.mynetty.protocol.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        GroupMessageRequestPacket groupMessageRequestPacket = new GroupMessageRequestPacket();
        String toGroupId = scanner.nextLine();
        String message = scanner.nextLine();
        groupMessageRequestPacket.setToGroupId(toGroupId);
        groupMessageRequestPacket.setMessage(message);
        channel.writeAndFlush(groupMessageRequestPacket);
    }
}