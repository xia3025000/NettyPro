package com.atguigu.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {

    public static void main(String[] args) throws Exception {
        String str = "hello, 尚硅谷";
        //创建一个输出流->channel
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/didi/file01.txt");
        //通过fileOutputStream获取对应的FileChannel
        //这个fileChannel真实类型是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将str放入byteBuffer
        byteBuffer.put(str.getBytes());

        //对byteBuffer 进行flip
        byteBuffer.flip();

        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }

}
