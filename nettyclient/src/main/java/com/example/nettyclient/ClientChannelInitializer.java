package com.example.nettyclient;


import com.example.nettyclient.coder.ClientResponseDecoder;
import com.example.nettyclient.coder.ClientRequestEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author zhaolei
 * Create: 2019/1/24 16:53
 * Modified By:
 * Description:
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final ClientRequestEncoder ENCODER = new ClientRequestEncoder();
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        // 添加编解码器, 由于ByteToMessageDecoder的子类无法使用@Sharable注解,
        // 这里必须给每个Handler都添加一个独立的Decoder.
        pipeline.addLast(ENCODER);
        pipeline.addLast(new ClientResponseDecoder());

        // and then business logic.
        pipeline.addLast(new ClientHandler());

    }
}