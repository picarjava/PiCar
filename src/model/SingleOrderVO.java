package model;

import java.sql.Date;
import java.sql.Timestamp;

public class SingleOrderVO {
    private String orderId;
    private String driverId;
    private Integer state;
    private Integer totalAmount;
    private String startLoc;
    private String endLoc;
    private Date startTime;
    private Date endTime;
    private Double startLng;
    private Double startLat;
    private Double endLng;
    private Double endLat;
    private Integer orderType;
    private Integer rate;
    private String note;
    private Timestamp lauchTime;
    
    public SingleOrderVO() {}
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    public String getDriverId() {
        return driverId;
    }
    
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    
    public Integer getState() {
        return state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
    
    public Integer getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getStartLoc() {
        return startLoc;
    }
    
    public void setStartLoc(String startLoc) {
        this.startLoc = startLoc;
    }
    
    public String getEndLoc() {
        return endLoc;
    }
    
    public void setEndLoc(String endLoc) {
        this.endLoc = endLoc;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    public Double getStartLng() {
        return startLng;
    }
    
    public void setStartLng(Double startLng) {
        this.startLng = startLng;
    }
    
    public Double getStartLat() {
        return startLat;
    }
    
    public void setStartLat(Double startLat) {
        this.startLat = startLat;
    }
    
    public Double getEndLng() {
        return endLng;
    }
    
    public void setEndLng(Double endLng) {
        this.endLng = endLng;
    }
    
    public Double getEndLat() {
        return endLat;
    }
    
    public void setEndLat(Double endLat) {
        this.endLat = endLat;
    }
    
    public Integer getOrderType() {
        return orderType;
    }
    
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
    
    public Integer getRate() {
        return rate;
    }
    
    public void setRate(Integer rate) {
        this.rate = rate;
    }
    
    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
    public Timestamp getLauchTime() {
        return lauchTime;
    }
    
    public void setLauchTime(Timestamp lauchTime) {
        this.lauchTime = lauchTime;
    }
}
