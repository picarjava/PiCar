package android.com.location.model;

public class InputInfo {
    private String driverID;
    private LatLng latLng;
    
    public InputInfo() {}
    
    public InputInfo(String driverID) {
        this.driverID = driverID;
    }
    
    public InputInfo(String driverID, LatLng latLng) {
        this(driverID);
        this.latLng = latLng;
    }
    
    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
