package com.groupMem.model;

public class GroupMemVO implements java.io.Serializable{
private String groupID;
private String MemID;
private Integer state;

public String getGroupID() {
	return groupID;
}
public void setGroupID(String groupId) {
	this.groupID = groupId;
}
public String getMemID() {
	return MemID;
}
public void setMemID(String memId) {
	MemID = memId;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}

}
