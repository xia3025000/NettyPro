package com.atguigu.mynetty.client.console;

import com.atguigu.mynetty.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        String userIds = scanner.nextLine();

        List<String> userList = Arrays.asList(userIds);
        createGroupRequestPacket.setUserIdList(userList);

        channel.writeAndFlush(createGroupRequestPacket);

    }
}
