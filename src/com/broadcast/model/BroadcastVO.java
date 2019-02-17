package com.broadcast.model;

public class BroadcastVO {
	
	
	private String msgID               ;
	private String memID               ;
	private String message              ;
	private Integer confirmed           ;
	
	public BroadcastVO() {
		
	}
	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Integer confirmed) {
		this.confirmed = confirmed;
	}
	
	

	
	
	

}
