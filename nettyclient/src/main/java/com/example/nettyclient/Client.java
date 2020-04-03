package com.example.nettyclient;

import com.example.nettyclient.myProtocal.MsgRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

/**
 * @author zhaolei
 * Create: 2019/1/24 16:54
 * Modified By:
 * Description:
 */
public class Client {
    public static void main(String[] args) throws Exception {
        byte[] bodyData = new byte[3];
        bodyData[0] = (byte) 1;
        byte[] didByte = Toolkit.shortToBigEndianByte((short) 1);
        System.arraycopy(didByte, 0, bodyData, 1, 2);
        byte CMD_ID_IFS_INDEX = 0x07;
        byte SUB_CMD_ID_DISK_STATUS_MANUAL_SET_RES = 0x01;

        MsgRequest msgRequest = new MsgRequest(CMD_ID_IFS_INDEX,SUB_CMD_ID_DISK_STATUS_MANUAL_SET_RES,bodyData);

        System.out.println(msgRequest.toString());

        sendMessage(msgRequest);
    }
    public static void sendMessage(MsgRequest msgRequest) throws InterruptedException{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)//
                    .handler(new ClientChannelInitializer());
            // Start the connection attempt.
            Channel ch = b.connect("192.168.101.52", 9001).sync().channel();
            ch.writeAndFlush(msgRequest);
            ch.close();
        } finally {
            group.shutdownGracefully();
        }
    }

}
