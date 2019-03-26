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
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
        InputInfo inputInfo = gson.fromJson(jsonObject.get("outputInfo"), InputInfo.class);
        if (jsonObject.has("memID")) {
            Map<String, Session> map = OrderBroadcastWebSocket.getMap();
            String memID = jsonObject.get("memID").getAsString();
            if (map != null) {
                Session memSession = map.get(memID);
                if (memSession != null && memSession.isOpen()) {
                    JsonObject jsonObjectOut = new JsonObject();
                    jsonObjectOut.addProperty("state", "callCar");
                    jsonObjectOut.addProperty("getIn", jsonObject.get("getIn").getAsBoolean());
                    jsonObjectOut.add("latLngs", jsonObject.get("latLngs"));
                    memSession.getAsyncRemote().sendText(jsonObjectOut.toString());
                } else
                    System.err.println("mem session is not exist!");
            } else
                System.err.println("OrderBroadcast map is null");
        }
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
        
        String text = String.format("driver session ID = %s, disconnected; close code = %d; reason phrase = %s",
                                    session.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
        System.out.println(text);
    }
    
    @OnError
    public void onError(Session session, Throwable e) {
        System.err.println(e.getMessage());
    }
    
    public static Map<String, StoredInfo> getMap() {
        return LocationWebSocket.driver;
    }
}
