package com.broadcast.model;

public class BroadcastVO {
	
	
	private String msg_Id               ;
	private String mem_Id               ;
	private String message              ;
	private Integer confirmed           ;
	
	public BroadcastVO() {
		
	}
	
	public String getMsg_Id() {
		return msg_Id;
	}

	public void setMsg_Id(String msg_Id) {
		this.msg_Id = msg_Id;
	}

	public String getMem_Id() {
		return mem_Id;
	}

	public void setMem_Id(String mem_Id) {
		this.mem_Id = mem_Id;
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
