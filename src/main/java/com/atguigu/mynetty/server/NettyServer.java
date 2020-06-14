package com.atguigu.mynetty.server;

import com.atguigu.mynetty.codec.PacketCodecHandler;
import com.atguigu.mynetty.codec.Spliter;
import com.atguigu.mynetty.handler.IMIdleStateHandler;
import com.atguigu.mynetty.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer {

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                        .addLast(new IMIdleStateHandler())
                        .addLast(new Spliter())
                        .addLast(PacketCodecHandler.INSTANCE)
                        .addLast(LoginRequestHandler.INSTANCE)
                        .addLast(HeartBeatRequestHandler.INSTANCE)
                        .addLast(AuthHandler.INSTANCE)
                        .addLast(MessageRequestHandler.INSTANCE)
                        .addLast(CreateGroupRequestHandler.INSTANCE)
                        .addLast(GroupMessageRequestHandler.INSTANCE)
                        .addLast(JoinGroupRequestHandler.INSTANCE);
                    }
                });
    }

    private static void bind() {

    }

}
