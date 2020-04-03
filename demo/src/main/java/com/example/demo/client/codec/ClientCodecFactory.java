package com.example.demo.client.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ClientCodecFactory implements ProtocolCodecFactory {
	String TAG = ClientCodecFactory.class.getName();

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return new ClientResponseDecoder();
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return new ClientRequestEncoder();
	}

}
