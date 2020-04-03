package com.example.demo.client;


import com.example.demo.client.codec.ClientCodecFactory;
import com.example.demo.client.msg.MsgRequest;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

public class Client extends IoHandlerAdapter {

	private String TAG = Client.class.getName();

	private IoSession session;

	private SocketConnector connector;

	private MsgRequest request;

	private MessageReceivedListener listener;

	private boolean hasReceiveData = false;

	public Client(String ip, int port) throws Exception {
		connect(ip, port);
	}

	public boolean isConnected() {
		return (session != null && session.isConnected());
	}

	public void connect(String ip, int port) throws Exception {
		try {
			disconnect();
			connector = new NioSocketConnector();
			connector.getFilterChain().addLast("codec",
					new ProtocolCodecFilter(new ClientCodecFactory()));
			connector.setHandler(this);
			// 设置连接超时为5秒
			connector.setConnectTimeoutMillis(5000);
			// 读写通道均在60秒内无任何操作就进入空闲状态
			connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);
			connector.getSessionConfig().setSoLinger(0);
			// 禁用纳格算法，将数据立即发送出去
			connector.getSessionConfig().setTcpNoDelay(true);
			connector.getSessionConfig().setWriteTimeout(30000);
			connector.getSessionConfig().setKeepAlive(true);
			ConnectFuture future = connector.connect(new InetSocketAddress(ip,
					port));
			// 等待是否连接成功
			future.awaitUninterruptibly();
			if (future.isDone()) {
				if (!future.isConnected()) { // 若在指定时间内没连接成功，则抛出异常
					connector.dispose(); // 不关闭的话会运行一段时间后抛出，too many open
											// files异常，导致无法连接
					throw new Exception("**fail to connect ip:" + ip + " port:"
							+ port);
				}
			}
			session = future.getSession();
			session.getService().getSessionConfig().setMaxReadBufferSize(1024);
		} catch (Exception e) {
			 throw new Exception(e);
		}

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
		if (hasReceiveData)
			return;
		// 还未收到响应数据，连接就断开了
		// processException(Defines.CONNECT_BREAK_ERROR_CODE);

		if (listener != null) {
			synchronized (listener) {
				listener.notify();
			}
			listener = null;
		}
		request = null;
	}

	// 指定时间内无读写
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		disconnect();
		if (hasReceiveData)
			return;
		// processException(Defines.NO_DATA_ERROR_CODE);
		if (listener != null) {
			synchronized (listener) {
				listener.notify();
			}
			listener = null;
		}
		request = null;
	}

	@Override
	public void messageReceived(IoSession session, Object object)
			throws Exception {
		hasReceiveData = true;
		byte[] allDataByte = (byte[]) object;
		if (listener != null) {
			listener.setResponseData(allDataByte);
			synchronized (listener) {
				listener.notify();
			}
			listener = null;
		}
		request = null;
		disconnect();
		// super.messageReceived(session, object);
	}

	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		if (listener != null) {
			synchronized (listener) {
				listener.notify();
			}
			listener = null;
		}
		disconnect();
		request = null;
	}

	public void disconnect() {
		try {
			if (session != null) {
				session.close(true);
				IoService ioService = session.getService();
				ioService.dispose();
				session = null;
				connector.dispose();
			}
		} catch (Exception e) {
		}
	}

	public void sendMsgRequest(MsgRequest request) {

		session.write(request);

	}

	public MessageReceivedListener getListener() {
		return listener;
	}

	public void setListener(MessageReceivedListener listener) {
		this.listener = listener;
	}

	private void processException(String errorCode) {
		// if (listener != null) {
		// Message message = new Message();
		// message.setResult(Message.RESULT_FAIL);
		// message.setErrCode(errorCode);
		// listener.receive(message.toResponse());
		// synchronized (listener) {
		// listener.notify();
		// }
		// }
		// if (request != null && request.getTaskId() != null) {
		// try {
		// Long taskId = request.getTaskId();
		// if (request.isBorrow()) {
		// taskService.processBandBorrow(taskId, Message.RESULT_FAIL,
		// errorCode, true, null, null, null);
		// } else if (request.isCancelBorrow())
		// taskService.processBandBorrow(taskId, Message.RESULT_FAIL,
		// errorCode, false, null, null, null);
		// else if (request.isLend())
		// taskService.processBandLend(taskId, Message.RESULT_FAIL,
		// errorCode, true, null, null, null);
		// else if (request.isCancelLend())
		// taskService.processBandLend(taskId, Message.RESULT_FAIL,
		// errorCode, false, null, null, null);
		// else
		// taskService.processUpdateTaskResult(
		// Defines.TABLE_PRE_OTHER, request.getTaskId(),
		// Message.RESULT_FAIL, errorCode);
		// } catch (Exception e) {
		// Log.e(TAG, "***processException throw exception:" + e);
		// }
		// }
	}

	public static String sendData(MessageReceivedListener listener, byte cmdId,
								  byte subCmdId, byte[] bodyData, String ip) throws Exception {
		try {
			String[] ipArray = ip.split(",");
			int length = ipArray.length;
			Client client = null;
			for (int i = 0; i < length; i++) {
				String ipData = ipArray[i];
				String[] array = ipData.split(":");
				Client clientTmp = null;
				try {
					clientTmp = new Client(array[0], Integer.parseInt(array[1]));
				} catch (Exception e) {
					continue;
				}
				client = clientTmp;
				break;
			}

			if (client == null)
				throw new Exception("未连上服务器");

			MsgRequest request = new MsgRequest();
			request.setCmdId(cmdId);
			request.setSubcmdId(subCmdId);
			request.setBodyData(bodyData);
			client.setListener(listener);
			client.sendMsgRequest(request);

			synchronized (listener) {
				listener.wait(10000);
			}
			return listener.getStatus();
		} catch (Exception e) {

			throw e;
		}
	}


}
