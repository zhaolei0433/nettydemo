package com.example.demo.client.msg;

public interface MessageReceivedListener {
	public void toSend();
	
	public void receivedResp(String respData);
	
	public void setTotalOnlineDuration(Integer duration);
	
	public void setCurrentDate(Long currentDate);
}
