package com.atguigu.mynetty.client.handler;

import com.atguigu.mynetty.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        String groupId = createGroupResponsePacket.getGroupId();
        List<String> userNameList = createGroupResponsePacket.getUserNameList();
    }
}
