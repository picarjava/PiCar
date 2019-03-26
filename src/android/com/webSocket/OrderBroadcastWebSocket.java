package android.com.webSocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
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

//if want get servlet from session need to use filter to get session and on modify handshake put session in endpoint config
@ServerEndpoint(value="/orderBroadcastWebSocket/{memID}", configurator=WebSocketConfig.class)
public class OrderBroadcastWebSocket {
    private final static Map<String, Session> member = new ConcurrentHashMap<>();
    private ServletContext servletContext;
    
    @OnOpen
    public void onOpen(@PathParam("memID") String memID, Session memberSession, EndpointConfig config) {
        member.put(memID, memberSession);
        System.out.println(memID + " is connected Session: " + memberSession);
        if (servletContext == null) {
            servletContext = ((HttpSession) config.getUserProperties().get("httpSession")).getServletContext();
            servletContext.setAttribute("OrderBroadcast", member);
        } // if
    } // onOpen()
    
    @OnMessage
    public void onMessage(Session session, String message) {
        
    }
    
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        for (String memID: member.keySet()) {
            if (session.equals(member.get(memID))) {
                member.remove(memID);
                break;
            } // if
        } // for
        
        String text = String.format("member session ID = %s, disconnected; close code = %d; reason phrase = %s",
                                    session.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
        System.out.println(text);
    }
    
    @OnError
    public void onError(Session session, Throwable e) {
        System.err.println(e.getMessage());
    }
    
    public static Map<String, Session> getMap() {
        return member;
    }
}
