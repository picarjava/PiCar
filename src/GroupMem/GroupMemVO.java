package GroupMem;

public class GroupMemVO implements java.io.Serializable{
private String groupId;
private String MemId;
private Integer state;

public String getGroupId() {
	return groupId;
}
public void setGroupId(String groupId) {
	this.groupId = groupId;
}
public String getMemId() {
	return MemId;
}
public void setMemId(String memId) {
	MemId = memId;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}

}
