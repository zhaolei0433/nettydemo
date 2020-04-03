package com.example.nettyclient.coder;

import com.example.nettyclient.Toolkit;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhaolei
 * Create: 2019/2/1 14:21
 * Modified By:
 * Description:  解码器，将入站的数据从一种格式转换成另外一种格式。
 */
public class ClientResponseDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out){

        System.out.println("接收的响应：");

        System.out.println(in.toString());

        byte[] pack_flag = new byte[2];
        in.readBytes(pack_flag);
        System.out.println("协议包开始标志："+ Toolkit.bigEndianByteToShort(pack_flag));

        byte[] pack_length = new byte[4];
        in.readBytes(pack_length);
        System.out.println("包长："+ Toolkit.bigEndianByteToInt(pack_length));

        byte[] version = new byte[1];
        in.readBytes(version);
        System.out.println("协议版本号："+ version[0]);

        byte[] byte_order = new byte[1];
        in.readBytes(byte_order);
        System.out.println("网络字节序："+ byte_order[0]);

        byte[] cmd_id = new byte[1];
        in.readBytes(cmd_id);
        System.out.println("数据包命令ID："+ cmd_id[0]);

        byte[] subcmd_id = new byte[1];
        in.readBytes(subcmd_id);
        System.out.println("数据包子命令ID："+ subcmd_id[0]);

        byte[] a7 = new byte[1];
        in.readBytes(a7);
        System.out.println("加密类型："+ a7[0]);

        byte[] serial_no = new byte[4];
        in.readBytes(serial_no);
        System.out.println("包序号："+ Toolkit.bigEndianByteToInt(serial_no));

        byte[] private_data = new byte[20];
        in.readBytes(private_data);
        System.out.println("私有数据："+ Toolkit.bigEndianByteToInt(private_data));

        byte[] check_code = new byte[4];
        in.readBytes(check_code);
        System.out.println("校验码："+ Toolkit.bigEndianByteToInt(check_code));

        byte[] body_byte = new byte[Toolkit.bigEndianByteToInt(pack_length) - 39];
        in.readBytes(body_byte);

        System.out.println("包体："+ Arrays.toString(body_byte));

        System.out.println("status："+ Toolkit.bigEndianByteToShort(Arrays.copyOfRange(body_byte, 0, 2)));
        System.out.println("state："+ Arrays.toString(body_byte));
        System.out.println("did："+ Arrays.toString(body_byte));
        //out.add(msgRequest);
    }
}
