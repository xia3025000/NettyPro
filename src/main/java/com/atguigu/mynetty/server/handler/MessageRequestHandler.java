package com.atguigu.mynetty.server.handler;

import com.atguigu.mynetty.protocol.request.MessageRequestPacket;
import com.atguigu.mynetty.protocol.response.MessageResponsePacket;
import com.atguigu.mynetty.session.Session;
import com.atguigu.mynetty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    private MessageRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        String toUserId = messageRequestPacket.getToUserId();
        String message = messageRequestPacket.getMessage();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(message);

        Channel toChannel = SessionUtil.getChannel(toUserId);
        toChannel.writeAndFlush(messageResponsePacket);
    }
}
