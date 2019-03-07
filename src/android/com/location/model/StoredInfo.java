package android.com.location.model;

import javax.websocket.Session;

public class StoredInfo {
    private Session session;
    private LatLng latlng;
    
    public StoredInfo(Session session) {
        this.session = session;
    }
    
    public StoredInfo(Session session, LatLng latlng) {
        this(session);
        this.latlng = latlng;
    }
    
    public Session getSession() {
        return session;
    }
    
    public void setSession(Session session) {
        this.session = session;
    }
    
    public LatLng getLatlng() {
        return latlng;
    }
    
    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }
    
    public boolean isOnline() {
        return session.isOpen();
    }
}
