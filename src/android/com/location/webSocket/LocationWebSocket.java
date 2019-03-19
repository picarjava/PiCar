package android.com.location.webSocket;

import java.util.Map;
import java.util.Set;
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

@ServerEndpoint(value="/locationWebSocket/{driverID}", configurator=LocationWebSocketConfig.class)
public class LocationWebSocket {
    private final static Map<String, StoredInfo> driver = new ConcurrentHashMap<>();
    private ServletContext servletContext;
    
    @OnOpen
    public void onOpen(@PathParam("driverID") String driverID, Session driverSession, EndpointConfig config) {
        driver.put(driverID, new StoredInfo(driverSession));
        System.out.println(driverID + " is connected Session: " + driverSession);
        servletContext = ((HttpSession) config.getUserProperties().get("httpSession")).getServletContext();
        servletContext.setAttribute("driverLocation", driver);
    } // onOpen()
    
    @OnMessage
    public void onMessage(Session session, String message) {
        InputInfo inputInfo = new Gson().fromJson(message, InputInfo.class);
        driver.put(inputInfo.getDriverID(), new StoredInfo(session, inputInfo.getLatLng()));
//        String jsonOut = "{\"singleOrder\":\"{\\\"orderID\\\":\\\"SODR042\\\",\\\"driverID\\\":\\\"d001\\\",\\\"endLat\\\":25.05842,\\\"endLng\\\":121.53163,\\\"endLoc\\\":\\\"10491台灣台北市中山區民生東路二段115巷6號\\\",\\\"memId\\\":\\\"M001\\\",\\\"orderType\\\":0,\\\"startLat\\\":25.02511,\\\"startLng\\\":121.55946000000002,\\\"startLoc\\\":\\\"110台灣台北市信義區信安街103巷201弄7號\\\",\\\"state\\\":0}\"}";
        System.out.println(message);
//        session.getAsyncRemote().sendText(jsonOut);
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
