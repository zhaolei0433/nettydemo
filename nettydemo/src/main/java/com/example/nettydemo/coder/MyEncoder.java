package com.example.nettydemo.coder;

import com.example.nettydemo.myProtocal.MyHeader;
import com.example.nettydemo.myProtocal.MyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

/**
 * @author zhaolei
 * Create: 2019/2/1 14:21
 * Modified By:
 * Description: 编码器，将出站的数据从一种格式转换成另外一种格式。
 */

@ChannelHandler.Sharable
public class MyEncoder extends MessageToByteEncoder<MyMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MyMessage message, ByteBuf out) throws Exception {

        // 将Message转换成二进制数据
        MyHeader header = message.getLuckHeader();

        // 这里写入的顺序就是协议的顺序.

        // 写入Header信息
        out.writeInt(header.getVersion());
        out.writeInt(message.getContent().length());
        out.writeBytes(header.getSessionId().getBytes());

        // 写入消息主体信息
        out.writeBytes(message.getContent().getBytes());
    }
}
