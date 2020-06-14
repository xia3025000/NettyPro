package com.atguigu.mynetty.util;

import com.atguigu.mynetty.attribute.Attribute;
import com.atguigu.mynetty.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    //服务端管理的Channel集合
    private static final Map<String, Channel> userIdToChannelMap = new ConcurrentHashMap<>();

    private static final Map<String, ChannelGroup> groupIdToChannelGroupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        channel.attr(Attribute.SESSION).set(session);
        userIdToChannelMap.put(session.getUserId(), channel);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session session = getSession(channel);
            userIdToChannelMap.remove(session.getUserId());
            channel.attr(Attribute.SESSION).set(null);
        }
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attribute.SESSION).get();
    }

    public static boolean hasLogin(Channel channel) {
        return getSession(channel) != null;
    }

    public static Channel getChannel(String userId) {
        return userIdToChannelMap.get(userId);
    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        groupIdToChannelGroupMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return groupIdToChannelGroupMap.get(groupId);
    }

}
