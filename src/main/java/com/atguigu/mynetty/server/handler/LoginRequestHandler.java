package com.atguigu.mynetty.server.handler;

import com.atguigu.mynetty.protocol.request.LoginRequestPacket;
import com.atguigu.mynetty.protocol.response.LoginResponsePacket;
import com.atguigu.mynetty.util.IDUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        //组装响应packet
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        //1.验证账号密码是否正确
        if (valid(msg)) {
            loginResponsePacket.setSuccess(true);
            String userId = IDUtil.generate();
            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setUserName(msg.getUserName());
        } else {
            loginResponsePacket.setSuccess(false);
        }

        ctx.writeAndFlush(loginResponsePacket);
    }

    //先默认为true
    private boolean valid(LoginRequestPacket packet) {
        return true;
    }
}
