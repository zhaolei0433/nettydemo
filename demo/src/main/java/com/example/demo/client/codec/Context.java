package com.example.demo.client.codec;

public class Context {
	private byte[] buffer = null;

	private int remainLength = 0;

	public byte[] getBuffer() {
		return buffer;
	}

	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}

	public int getRemainLength() {
		return remainLength;
	}

	public void setRemainLength(int remainLength) {
		this.remainLength = remainLength;
	}
}
