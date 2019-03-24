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

@ServerEndpoint(value="/BroadcastOrderServer/{adminID}", configurator=BroadcastConfig.class)
public class BroadcastOrderServer {
	private static final long serialVersionUID = 1L;
	private static final Map<String,Session> map=(new ConcurrentHashMap<>());
    private static final Set<Session> allSessions=Collections.synchronizedSet(new HashSet<Session>());

	//收到客戶端 訊息
    //session 拿websocket
    //contrll  拿Onopen的map getsession
//    網頁 跟ajax不同
  //get(VO.id).getasyncremo.send 。JSON。格式 [string]
	@OnOpen
	public void onOpen(@PathParam("adminID")String adminID,Session userSession,EndpointConfig config)throws IOException{
	allSessions.add(userSession);
	map.put(adminID,userSession);
//	onOpenadminID=adminID;
	ServletContext servletContext = ((HttpSession) config.getUserProperties().get("httpSession")).getServletContext();
    servletContext.setAttribute("broadcastOrderMap", map);
    
	String message= "{\"message\":\"" +"Picar推播:"+"\"}";
	userSession.getBasicRemote().sendText(message);
	System.out.println(adminID+"已連線");
	
	
	}
	
	@OnMessage
	public void onMessage(@PathParam("adminID")String adminID,Session userSession,String message) {
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
