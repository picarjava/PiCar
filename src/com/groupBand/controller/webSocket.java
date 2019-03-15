package com.groupBand.controller;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.groupOrder.model.GroupOrderVO;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/webSocket/{myName}/{myRoom}")
public class webSocket {
private static final Map<Session,String> map =Collections.synchronizedMap(new HashMap<>());	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
//public List<String> list =new ArrayList<String>();
	@OnOpen
	public void onOpen(@PathParam("myName") String myName, @PathParam("myRoom") String myRoom, Session userSession) throws IOException {
		allSessions.add(userSession);
		map.put(userSession, myRoom);
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(myName + ": 已連線");
		System.out.println(myRoom + ": 房號");
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}

	
	@OnMessage
	public void onMessage( @PathParam("myRoom") String myRoom,Session userSession, String message) {
		
//		this.list.add(message);
//		
//		for(String elements : list) {		
			
			

		for (Session session : allSessions) {//轉傳
			
			if (session.isOpen()&& map.get(session).equals(myRoom)) 
		session.getAsyncRemote().sendText(message);
		
		}
	
		
		
		}
			
	
	@OnError
	public void onError(Session userSession, Throwable e){
//}		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}
