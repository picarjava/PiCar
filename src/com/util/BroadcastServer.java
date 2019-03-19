package com.util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/BroadcastServer/{memID}")
public class BroadcastServer {
	private static final long serialVersionUID = 1L;
    private static final Set<Session> allSessions=Collections.synchronizedSet(new HashSet<Session>());
	String onOpenmemID;
	Session onOpenSession;
    
	@OnOpen
	public void onOpen(@PathParam("memID")String memID,Session userSession)throws IOException{
	allSessions.add(userSession);
	onOpenmemID=memID;
	onOpenSession=userSession;
	userSession.getBasicRemote().sendText("WebSocket連線成功");
	}
	
	//若此會員有開啟session 則傳送推播
	public void broadcast(String memID,String message) {
		if(onOpenSession != null) {
			if(onOpenSession.isOpen()){
			 this.onMessage(memID,onOpenSession,message);
			}
		} 
	}
	
	
	@OnMessage
	public void onMessage(@PathParam("memID")String memID,Session userSession,String message) {
		for(Session session:allSessions) {
			if(session.isOpen()&&memID.equals(onOpenmemID)){ //若onOpen的memID 為排程器要傳送的memID，則傳送訊息
				message="測試";
				session.getAsyncRemote().sendText(message);
			}
		}
	}
	
	@OnError
	public void onError(Session userSession, Throwable e) {
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId()+"Disconnect:"+Integer.toString(reason.getCloseCode().getCode()));
	}
	
}
