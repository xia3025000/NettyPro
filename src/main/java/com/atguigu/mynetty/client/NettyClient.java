package com.atguigu.mynetty.client;

import com.atguigu.mynetty.client.console.ConsoleCommandManager;
import com.atguigu.mynetty.client.console.LoginConsoleCommand;
import com.atguigu.mynetty.client.handler.*;
import com.atguigu.mynetty.codec.PacketCodecHandler;
import com.atguigu.mynetty.codec.Spliter;
import com.atguigu.mynetty.handler.IMIdleStateHandler;
import com.atguigu.mynetty.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class NettyClient {

    public static void main(String[] args) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                        .addLast(new IMIdleStateHandler())
                        .addLast(new Spliter())
                        .addLast(PacketCodecHandler.INSTANCE)
                        .addLast(new LoginResponseHandler())
                        .addLast(new MessageResponseHandler())
                        .addLast(new CreateGroupResponseHandler())
                        .addLast(new JoinGroupResponseHandler())
                        .addLast(new LogoutResponseHandler())
                        .addLast(new HeartBeatTimerHandler());

                    }
                });


    }

    private void connect(Bootstrap bootstrap, String host, int port) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {

            }
        });
    }

    private void startConsoleThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!SessionUtil.hasLogin(channel)) {
                    //未登录,必须先登录
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    //已登录
                    consoleCommandManager.exec(scanner, channel);
                }

            }
        }).start();
    }



}
