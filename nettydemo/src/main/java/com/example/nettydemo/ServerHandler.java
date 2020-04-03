package com.example.nettydemo;

import com.example.nettydemo.myProtocal.MyHeader;
import com.example.nettydemo.myProtocal.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author zhaolei
 * Create: 2019/1/24 16:05
 * Modified By:
 * Description:
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        System.out.println("接收的客户端消息："+msg.toString());
        int version = 1;
        String sessionId = UUID.randomUUID().toString();
        String content = "Welcome! zhaolei";

        MyHeader header = new MyHeader(version, content.length(), sessionId);
        MyMessage message = new MyMessage(header, content);

        System.out.println("响应客户端消息"+message);

        channelHandlerContext.writeAndFlush(message);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

      /*  int version = 1;
        String sessionId = UUID.randomUUID().toString();
        String content = "Welcome! zhaolei";

        MyHeader header = new MyHeader(version, content.length(), sessionId);
        MyMessage message = new MyMessage(header, content);

        System.out.println("响应客户端消息"+message);

        ctx.writeAndFlush(message);*/
    }
}