package com.atguigu.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class NIOFileChannel04 {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("/Users/didi/a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/didi/a2.jpg");

        FileChannel sourceChannel = fileInputStream.getChannel();
        FileChannel destChannel = fileOutputStream.getChannel();

        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        destChannel.close();
        sourceChannel.close();
        fileOutputStream.close();
        fileInputStream.close();
    }

}
