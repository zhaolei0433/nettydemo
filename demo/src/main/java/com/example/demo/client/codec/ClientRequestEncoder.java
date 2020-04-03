package com.example.demo.client.codec;


import com.example.demo.Toolkit;
import com.example.demo.client.msg.MsgRequest;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.ByteOrder;

public class ClientRequestEncoder implements ProtocolEncoder {
	private String TAG = ClientRequestEncoder.class.getName();
	private short packFlag = 0X55CC;// 协议包开始标志

	@Override
	public void encode(IoSession session, Object object,
					   ProtocolEncoderOutput out) throws Exception {
		MsgRequest requestData = (MsgRequest) object;
		byte[] bodyByte = requestData.getBodyData();
		byte cmdId = requestData.getCmdId();
		byte subCmdId = requestData.getSubcmdId();
		int serialNo = 1;
		byte[] serialNoByte = Toolkit.intToBigEndianByte(serialNo);
		byte[] privateDataByte = new byte[20];
		byte[] checkCode = new byte[4];

		// 长度
		int msgLength = 0;
		if (bodyByte != null)
			msgLength = bodyByte.length;


		// 报文总长度
		int totalMsgLength = msgLength + 39;
		byte[] lengthByte = Toolkit.intToBigEndianByte(totalMsgLength);
		// Log.i(TAG, "****msgLength:" + totalMsgLength + " msgLength:"
		// + msgLength);

		IoBuffer message = IoBuffer.allocate(totalMsgLength);
		message.order(ByteOrder.BIG_ENDIAN);// 网络字节顺序采用big endian排序方式

		byte[] packFlagByte = Toolkit.shortToBigEndianByte(packFlag);

		message.put(packFlagByte); // 协议包开始标志
		message.put(lengthByte);// 包长
		message.put((byte) 1); // 协议版本号
		message.put((byte) 2);// 网络字节序
		message.put(cmdId);// 数据包命令ID
		message.put(subCmdId);// 数据包子命令ID
		message.put((byte) 0);// 加密类型
		message.put(serialNoByte);// 数据包序列号
		message.put(privateDataByte);// 协议私有数据
		message.put(checkCode);// 校验码
		if (bodyByte != null)
			message.put(bodyByte);

		message.flip();
		session.write(message);


	}

	public void dispose(IoSession arg0) throws Exception {

	}

}
