package com.atguigu.mynetty.client.console;

import com.atguigu.mynetty.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String toUserId = scanner.nextLine();
        String message = scanner.nextLine();
        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
        messageRequestPacket.setToUserId(toUserId);
        messageRequestPacket.setMessage(message);
        channel.writeAndFlush(messageRequestPacket);
    }
}
