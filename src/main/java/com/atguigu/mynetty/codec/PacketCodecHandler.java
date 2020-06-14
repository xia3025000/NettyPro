package com.atguigu.mynetty.codec;

import com.atguigu.mynetty.protocol.Packet;
import com.atguigu.mynetty.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {
    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

    private PacketCodecHandler() {

    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        //将msg进行编码后,放out发出
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        //填充ByteBuf
        PacketCodec.INSTANCE.encode(byteBuf, msg);
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        Packet packet = PacketCodec.INSTANCE.decode(msg);
        out.add(packet);
    }
}
