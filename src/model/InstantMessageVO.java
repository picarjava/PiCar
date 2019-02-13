package model;

import java.sql.Date;

public class InstantMessageVO {
    public InstantMessageVO() {}
    
    private String memId;
    private String msgRecord;
    private Date startTime;

    public String getMemId() {
        return memId;
    }
    
    public void setMemId(String memId) {
        this.memId = memId;
    }
    
    public String getMsgRecord() {
        return msgRecord;
    }
    
    public void setMsgRecord(String msgRecord) {
        this.msgRecord = msgRecord;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
