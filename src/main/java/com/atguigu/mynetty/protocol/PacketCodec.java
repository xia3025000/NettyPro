package com.atguigu.mynetty.protocol;

import com.atguigu.mynetty.protocol.request.LoginRequestPacket;
import com.atguigu.mynetty.serialize.Serializer;
import com.atguigu.mynetty.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.atguigu.mynetty.protocol.command.Command.LOGIN_REQUEST;

public class PacketCodec {
    public static final PacketCodec INSTANCE = new PacketCodec();
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;
    public static final int MAGIC_NUMBER = 0x12345678;

    private PacketCodec() {
        //一次性初始化,不涉及增减,所以普通hashMap就可以了
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap<>();
        JSONSerializer jsonSerializer = new JSONSerializer();
        //根据序列化算法号找对应的序列化算法
        serializerMap.put(jsonSerializer.gerSerializerAlgorithm(), jsonSerializer);
    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        //编码协议:
        //魔数(4)、版本号(1)、序列化算法号(1)、指令(1)、数据长度(4)、数据体(n字节)
        byteBuf.writeInt(MAGIC_NUMBER);
        //当前不分版本
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(packet.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byte[] bytes = serializerMap.get(packet.getSerializerAlgorithm()).serialize(packet);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf) {
        //魔数默认成功
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);
        byte serializeAlgorithm = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = packetTypeMap.get(command);
        Serializer serializer = serializerMap.get(serializeAlgorithm);
        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        //不成功返回null,代表没接收到有效信息,后续handler对此不处理
        return null;
    }


}
