package android.com.member.model;

import com.member.model.MemberVO;

public interface MemberDAO_Interface {
    MemberVO findByEmailAndPassword(String email, String password);
    byte[] getPictureByMemID(String memID);
    void updatePreferenceByMemID(Integer pet, Integer smoke, Integer babySeat, String memID);
}
