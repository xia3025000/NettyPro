package com.atguigu.mynetty.client.handler;

import com.atguigu.mynetty.protocol.response.LoginResponsePacket;
import com.atguigu.mynetty.session.Session;
import com.atguigu.mynetty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        boolean success = loginResponsePacket.isSuccess();
        String userName = loginResponsePacket.getUserName();

        if (success) {
            Session session = new Session(userId, userName);
            SessionUtil.bindSession(session, ctx.channel());
        } else {

        }
    }
}
