package com.atguigu.mynetty.attribute;

import com.atguigu.mynetty.session.Session;
import io.netty.util.AttributeKey;

public interface Attribute {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
