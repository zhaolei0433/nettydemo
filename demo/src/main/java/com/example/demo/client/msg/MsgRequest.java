package com.example.demo.client.msg;

public class MsgRequest {

	private byte cmdId;

	private byte subcmdId;
	
	private byte[] bodyData;

	public byte getCmdId() {
		return cmdId;
	}

	public void setCmdId(byte cmdId) {
		this.cmdId = cmdId;
	}

	public byte getSubcmdId() {
		return subcmdId;
	}

	public void setSubcmdId(byte subcmdId) {
		this.subcmdId = subcmdId;
	}

	public byte[] getBodyData() {
		return bodyData;
	}

	public void setBodyData(byte[] bodyData) {
		this.bodyData = bodyData;
	}


	
	
}
