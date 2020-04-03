package com.example.nettyclient.myProtocal;

import lombok.Data;

@Data
public class MsgRequest {
	private byte cmdId;
	private byte subcmdId;
	private byte[] bodyData;

	public MsgRequest() {
	}

	public MsgRequest(byte cmdId, byte subcmdId, byte[] bodyData) {
		this.cmdId = cmdId;
		this.subcmdId = subcmdId;
		this.bodyData = bodyData;
	}
}
