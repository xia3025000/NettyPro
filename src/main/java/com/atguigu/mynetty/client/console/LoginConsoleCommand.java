package com.atguigu.mynetty.client.console;

import com.atguigu.mynetty.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        String userName = scanner.nextLine();
        String password = scanner.nextLine();
        loginRequestPacket.setUserName(userName);
        loginRequestPacket.setPassword(password);
        channel.writeAndFlush(loginRequestPacket);
        try {
            //等待1秒登录响应
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
