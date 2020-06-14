package com.atguigu.mynetty.client.console;

import com.atguigu.mynetty.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand{

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put(ConsoleCommand.SEND_TO_USER, new SendToUserConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.CREATE_GROUP, new CreateGroupConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.SEND_TO_GROUP, new SendToGroupConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.JOIN_GROUP, new JoinGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command = scanner.nextLine();

        if (!SessionUtil.hasLogin(channel)) {
            return;
        }

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {

        }

    }
}
