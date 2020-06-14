package com.atguigu.mynetty.server.handler;


import com.atguigu.mynetty.protocol.request.CreateGroupRequestPacket;
import com.atguigu.mynetty.protocol.response.CreateGroupResponsePacket;
import com.atguigu.mynetty.util.IDUtil;
import com.atguigu.mynetty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();

        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        List<String> userNameList = new ArrayList<>();

        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        String groupId = IDUtil.generate();
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setUserNameList(userNameList);



        channelGroup.writeAndFlush(createGroupResponsePacket);

        SessionUtil.bindChannelGroup(groupId, channelGroup);

    }
}
