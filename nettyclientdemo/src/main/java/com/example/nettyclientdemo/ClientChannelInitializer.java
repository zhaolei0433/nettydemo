package com.example.nettyclientdemo;

import com.example.nettyclientdemo.coder.MyDecoder;
import com.example.nettyclientdemo.coder.MyEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaolei
 * Create: 2019/1/24 16:53
 * Modified By:
 * Description:
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final MyEncoder ENCODER = new MyEncoder();
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        // 添加编解码器, 由于ByteToMessageDecoder的子类无法使用@Sharable注解,
        // 这里必须给每个Handler都添加一个独立的Decoder.
        pipeline.addLast(ENCODER);
        pipeline.addLast(new MyDecoder());

        // and then business logic.
        pipeline.addLast(new ClientHandler());

    }
}