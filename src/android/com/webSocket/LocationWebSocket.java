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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.com.location.model.InputInfo;
import android.com.location.model.StoredInfo;

// if want get servlet from session need to use filter to get session and on modify handshake put session in endpoint config
@ServerEndpoint(value="/locationWebSocket/{driverID}", configurator=WebSocketConfig.class)
public class LocationWebSocket {
    private final static Map<String, StoredInfo> driver = new ConcurrentHashMap<>();
    private ServletContext servletContext;
    
    @OnOpen
    public void onOpen(@PathParam("driverID") String driverID, Session driverSession, EndpointConfig config) {
        driver.put(driverID, new StoredInfo(driverSession));
        System.out.println(driverID + " is connected Session: " + driverSession);
        if (servletContext == null) {
            servletContext = ((HttpSession) config.getUserProperties().get("httpSession")).getServletContext();
            servletContext.setAttribute("driverLocation", driver);
        }
    } // onOpen()
    
    @OnMessage
    public void onMessage(Session session, String message) {
        JsonObject jsonObject = new Gson().fromJson(message, JsonObject.class);
        InputInfo inputInfo = new Gson().fromJson(jsonObject.get("outputInfo"), InputInfo.class);
        driver.put(inputInfo.getDriverID(), new StoredInfo(session, inputInfo.getLatLng(), inputInfo.isExecuting()));
        System.out.println(message);
    }
    
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        for (String driverID: driver.keySet()) {
            if (driver.get(driverID).getSession().equals(session)) {
                driver.remove(driverID);
                break;
            } // if
        } // for
        
        String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
                                    session.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
        System.out.println(text);
    }
    
    @OnError
    public void onError(Session session, Throwable e) {
        System.err.println(e.getMessage());
    }
}
