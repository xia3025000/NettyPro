package com.atguigu.mynetty.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 命令与指令不同:
 * 命令代表用户一种操作
 * 指令表示包的一种功能
 */
public interface ConsoleCommand {

    String SEND_TO_USER = "sendToUser";
    String CREATE_GROUP = "createGroup";
    String LOGOUT = "logout";
    String SEND_TO_GROUP = "sendToGroup";
    String JOIN_GROUP = "joinGroup";

    void exec(Scanner scanner, Channel channel);

}
