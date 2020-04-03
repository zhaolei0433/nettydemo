package com.example.nettyclientdemo.coder;

import com.example.nettyclientdemo.myProtocal.MyHeader;
import com.example.nettyclientdemo.myProtocal.MyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author zhaolei
 * Create: 2019/2/1 14:21
 * Modified By:
 * Description: 编码器，将出站的数据从一种格式转换成另外一种格式。
 */

@ChannelHandler.Sharable
public class MyEncoder extends MessageToByteEncoder<MyMessage> {
    private final short packFlag = 0X55CC;// 协议包开始标志
    @Override
    protected void encode(ChannelHandlerContext ctx, MyMessage message, ByteBuf out) throws Exception {
      // 将Message转换成二进制数据
        MyHeader header = message.getLuckHeader();
        // 这里写入的顺序就是协议的顺序
        // 写入Header信息
        out.writeInt(header.getVersion());
        out.writeInt(header.getContentLength());
        out.writeBytes(header.getSessionId().getBytes());
        // 写入消息主体信息
        out.writeBytes((message.getContent()).getBytes());

        /*System.out.println("测试！");
        System.out.println("输出数据："+ out.readInt());
        System.out.println("输出数据："+ out.readInt());
        byte[] sessionByte = new byte[36];
        out.readBytes(sessionByte);
        System.out.println("输出数据："+ new String(sessionByte));
        byte[] contentLength = new byte[10];
        out.readBytes(contentLength);
        System.out.println("输出数据："+ new String(contentLength));*/

    }
}
