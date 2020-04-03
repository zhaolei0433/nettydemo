package com.example.nettyclientdemo;

import com.example.nettyclientdemo.myProtocal.MyHeader;
import com.example.nettyclientdemo.myProtocal.MyMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

/**
 * @author zhaolei
 * Create: 2019/1/24 16:54
 * Modified By:
 * Description:
 */
public class Client {
    static final String HOST = System.getProperty("host", "192.168.20.35");
    static final int PORT = Integer.parseInt(System.getProperty("port", "7000"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {
        int version = 1;
        String sessionId = UUID.randomUUID().toString();
        String content = "hi,I am zhaolei";

        MyHeader header = new MyHeader(version, content.length(), sessionId);
        MyMessage message = new MyMessage(header, content);

        System.out.println(message);
        sendMessage(message);
    }
    public static void sendMessage(MyMessage message) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)//
                    .handler(new ClientChannelInitializer());
            // Start the connection attempt.
            Channel ch = b.connect("127.0.0.1", 7000).sync().channel();
            ch.writeAndFlush(message);
            ch.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

}
