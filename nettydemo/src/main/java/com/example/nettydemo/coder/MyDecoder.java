package com.example.nettydemo.coder;

import com.example.nettydemo.myProtocal.MyHeader;
import com.example.nettydemo.myProtocal.MyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zhaolei
 * Create: 2019/2/1 14:21
 * Modified By:
 * Description:  解码器，将入站的数据从一种格式转换成另外一种格式。
 */
public class MyDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // 获取协议的版本
        int version = in.readInt();
        // 获取消息长度
        int contentLength = in.readInt();
        // 获取SessionId
        byte[] sessionByte = new byte[36];
        in.readBytes(sessionByte);
        String sessionId = new String(sessionByte);
        // 组装协议头
        MyHeader header = new MyHeader(version, contentLength, sessionId);

        // 读取消息内容
        byte[] contentByte = new byte[contentLength];
        in.readBytes(contentByte);
        String content = new String(contentByte);
        MyMessage message = new MyMessage(header, content);

       /* byte[] content = in.readBytes(in.readableBytes()).array();
        MyMessage message = new MyMessage(header, new String(content));*/
        out.add(message);
    }
}
