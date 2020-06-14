package com.atguigu.mynetty.server.handler;

import com.atguigu.mynetty.protocol.request.GroupMessageRequestPacket;
import com.atguigu.mynetty.protocol.response.GroupMessageResponsePacket;
import com.atguigu.mynetty.session.Session;
import com.atguigu.mynetty.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    private GroupMessageRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        String toGroupId = groupMessageRequestPacket.getToGroupId();
        String message = groupMessageRequestPacket.getMessage();

        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(toGroupId);

        groupMessageResponsePacket.setFromGroupId(toGroupId);

        Session fromUserSession = SessionUtil.getSession(ctx.channel());
        groupMessageResponsePacket.setFromUser(fromUserSession);
        groupMessageResponsePacket.setMessage(message);

        channelGroup.writeAndFlush(groupMessageResponsePacket);
    }
}
