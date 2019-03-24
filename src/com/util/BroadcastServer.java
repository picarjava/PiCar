package com.util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import android.com.webSocket.WebSocketConfig;

@ServerEndpoint(value="/BroadcastServer/{memID}", configurator=BroadcastConfig.class)
public class BroadcastServer {
	private static final long serialVersionUID = 1L;
	private static final Map<String,Session> map=(new ConcurrentHashMap<>());
    private static final Set<Session> allSessions=Collections.synchronizedSet(new HashSet<Session>());

	
    
	@OnOpen
	public void onOpen(@PathParam("memID")String memID,Session userSession,EndpointConfig config)throws IOException{
	allSessions.add(userSession);
	map.put(memID,userSession);
//	onOpenmemID=memID;
	ServletContext servletContext = ((HttpSession) config.getUserProperties().get("httpSession")).getServletContext();
    servletContext.setAttribute("broadcastMap", map);
    
	String message= "{\"message\":\"" +"Picar推播系統連線成功!"+"\"}";
	userSession.getBasicRemote().sendText(message);
	System.out.println(memID+"已連線");
	
	
	}
	
	@OnMessage
	public void onMessage(@PathParam("memID")String memID,Session userSession,String message) {
		for(Session session:allSessions) {
			if(session.isOpen()){ 
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
}
