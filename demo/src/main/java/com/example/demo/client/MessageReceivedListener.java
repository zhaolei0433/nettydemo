package com.example.demo.client;

import java.util.List;

public interface MessageReceivedListener {
	
//	public void toWriteResult();
	
	public void setResponseData(byte[] responseData);
	
	public String getStatus();
	
	public List<String[]> getDiskLoadInfoList();

}
