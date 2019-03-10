package android.com.member.model;

import java.sql.Date;

public class MemberVO {
    private String memID;
    private String name;
    private String email;
    private String phone;
    private String creditCard;
    private Integer pet;
    private Integer smoke;
    private Integer gender;
    private Integer token;
    private Integer activityToken;
    private Date birthday;
    private Integer verified;
    private Integer babySeat;

    public String getMemID() {
        return memID;
    }

    public void setMemID(String memID) {
        this.memID = memID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public Integer getPet() {
        return pet;
    }

    public void setPet(Integer pet) {
        this.pet = pet;
    }

    public Integer getSmoke() {
        return smoke;
    }

    public void setSmoke(Integer smoke) {
        this.smoke = smoke;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public Integer getActivityToken() {
        return activityToken;
    }

    public void setActivityToken(Integer activityToken) {
        this.activityToken = activityToken;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public Integer getBabySeat() {
        return babySeat;
    }

    public void setBabySeat(Integer babySeat) {
        this.babySeat = babySeat;
    }
    
    public static MemberVO castToAndroidMemberVO(com.member.model.MemberVO memberVO) {
        MemberVO newMemberVO = null;
        if (memberVO != null) {
            newMemberVO = new MemberVO();
            newMemberVO.setMemID(memberVO.getMemID());
            newMemberVO.setEmail(memberVO.getEmail());
            newMemberVO.setName(memberVO.getName());
            newMemberVO.setGender(memberVO.getGender());
            newMemberVO.setBirthday(memberVO.getBirthday());
            newMemberVO.setToken(memberVO.getToken());
            newMemberVO.setActivityToken(memberVO.getActivityToken());
            newMemberVO.setCreditCard(memberVO.getCreditcard());
            newMemberVO.setBabySeat(memberVO.getBabySeat());
            newMemberVO.setPet(memberVO.getPet());
            newMemberVO.setPhone(memberVO.getPhone());
            newMemberVO.setSmoke(memberVO.getSmoke());
            newMemberVO.setVerified(memberVO.getVerified());
        }
        
        return newMemberVO;
    }
}

