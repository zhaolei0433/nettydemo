package com.example.nettydemo;

import com.example.nettydemo.coder.MyDecoder;
import com.example.nettydemo.coder.MyEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
/**
 * @author zhaolei
 * Create: 2019/1/24 16:04
 * Modified By:
 * Description:
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final MyEncoder ENCODER = new MyEncoder();
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 添加编解码器, 由于ByteToMessageDecoder的子类无法使用@Sharable注解,
        // 这里必须给每个Handler都添加一个独立的Decoder.
        channel.pipeline().addLast(ENCODER);
        channel.pipeline().addLast(new MyDecoder());
        channel.pipeline().addLast(new ServerHandler());
    }
}