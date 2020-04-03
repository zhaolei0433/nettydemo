package com.example.nettyclient.coder;

import com.example.nettyclient.Toolkit;
import com.example.nettyclient.myProtocal.MsgRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author zhaolei
 * Create: 2019/2/1 14:21
 * Modified By:
 * Description: 编码器，将出站的数据从一种格式转换成另外一种格式。
 */

@ChannelHandler.Sharable
public class ClientRequestEncoder extends MessageToByteEncoder<MsgRequest> {
    private final short packFlag = 0X55CC;// 协议包开始标志
    @Override
    protected void encode(ChannelHandlerContext ctx, MsgRequest msgRequest, ByteBuf out) throws Exception {
        byte[] bodyByte = msgRequest.getBodyData();
        byte cmdId = msgRequest.getCmdId();
        byte subCmdId = msgRequest.getSubcmdId();
        byte[] privateDataByte = new byte[20];
        byte[] checkCode = new byte[4];
        // 长度
        int msgLength = 0;
        if (bodyByte != null)
            msgLength = bodyByte.length;
        // 报文总长度
        int totalMsgLength = msgLength + 39;
        out.writeBytes(Toolkit.shortToBigEndianByte(packFlag)); // 协议包开始标志 -- 2字节
        out.writeBytes(Toolkit.intToBigEndianByte(totalMsgLength)); // 包长 -- 4字节
        out.writeByte((byte) 1); // 协议版本号 -- 1字节
        out.writeByte((byte) 2); // 网络字节序 -- 1字节
        out.writeByte(cmdId); // 数据包命令ID  -- 1字节
        out.writeByte(subCmdId); // 数据包子命令ID -- 1字节
        out.writeByte((byte) 0); // 加密类型 -- 1字节
        out.writeBytes(Toolkit.intToBigEndianByte(1)); // 数据包序列号 -- 4字节
        out.writeBytes(privateDataByte); // 协议私有数据 -- 20字节
        out.writeBytes(checkCode); // 校验码 -- 4字节
        if (bodyByte != null)
            out.writeBytes(bodyByte); // 包体

        System.out.println("测试："+out.toString());
    }
}
