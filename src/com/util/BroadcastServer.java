package com.util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
	private static final Map<Session,String> map =Collections.synchronizedMap(new HashMap<>());
    private static final Set<Session> allSessions=Collections.synchronizedSet(new HashSet<Session>());
	String onOpenmemID;
	private Session onOpenSession;
    
	@OnOpen
	public void onOpen(@PathParam("memID")String memID,Session userSession)throws IOException{
	allSessions.add(userSession);
	onOpenmemID=memID;
	setOnOpenSession(userSession);
	String message= "{\"message\":\"" +"WebSocket連線成功了"+"\"}";
	userSession.getBasicRemote().sendText(message);
	System.out.println(memID+"已連線");
	System.out.println("onOpenSession是否不空"+ (getOnOpenSession() != null));
	
	}
	
	//若此會員有開啟session 則傳送推播
	public void broadcast(String memID ,String message) {
		System.out.println("有進broadcast方法 "+ "onOpenSession非空值"+ (getOnOpenSession() != null) );
		
		if(getOnOpenSession() != null) {
			try {
				this.onOpen(memID,getOnOpenSession());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			String toJsonMessage= "{\"message\":\"" +message+"\"}";
			this.onMessage(memID,getOnOpenSession(),message);
		} 
	}
	
	
	@OnMessage
	public void onMessage(@PathParam("memID")String memID,Session userSession,String message) {
		for(Session session:allSessions) {
			if(session.isOpen()&&  onOpenmemID.equals(memID)){ //若onOpen的memID 為排程器要傳送的memID，則傳送訊息
				session.getAsyncRemote().sendText(message);
				System.out.println("廣播成功");
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

	public Session getOnOpenSession() {
		return onOpenSession;
	}

	public void setOnOpenSession(Session onOpenSession) {
		this.onOpenSession = onOpenSession;
	}
	
}
