package com.example.demo.client.codec;


import com.example.demo.Toolkit;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class ClientResponseDecoder extends CumulativeProtocolDecoder {
	private String TAG = ClientResponseDecoder.class.getName();

	protected boolean doDecode(IoSession session, IoBuffer buffer,
							   ProtocolDecoderOutput out) throws Exception {
		try {

			while (buffer.hasRemaining()) {
				Object obj = session.getAttribute("content");
				if (obj == null) {
					// 报头未接收完整
					if (buffer.remaining() < 39)
						return false;
					int remaingLen = buffer.remaining();

					byte[] headerBytes = new byte[remaingLen];
					buffer.get(headerBytes);

					// 整个报文的长度，包括报头
					byte[] lenBytes = new byte[4];
					System.arraycopy(headerBytes, 2, lenBytes, 0, 4);

					int dataLen = Toolkit.bigEndianByteToInt(lenBytes);// 长度已包括了报头

					// 数据已接收完成
					if (headerBytes.length == dataLen) {
						out.write(headerBytes);
						return true;
					} else {
						byte[] contextByte = new byte[headerBytes.length]; // 保存下接收到的报头内容
						System.arraycopy(headerBytes, 0, contextByte, 0,
								contextByte.length);
						Context context = new Context();
						context.setRemainLength(dataLen-contextByte.length);
						context.setBuffer(contextByte);// 保存下接收到的报头内容
						session.setAttribute("content",
								context);
					}
				}
				Context context = (Context) session
						.getAttribute("content");
				if (buffer.remaining() >= context.getRemainLength()) {
					byte[] b = new byte[context.getRemainLength()];
					buffer.get(b);
					byte[] all = new byte[context.getBuffer().length + b.length];
					System.arraycopy(context.getBuffer(), 0, all, 0,
							context.getBuffer().length);
					System.arraycopy(b, 0, all, context.getBuffer().length,
							b.length);
					session.removeAttribute("content");
					out.write(all);
					return true;
				} else {
					return false;
				}
			}
			return false;
		} catch (Exception e) {

			throw e;
		}
	}

	private void print(byte[] data) {

		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}
}
