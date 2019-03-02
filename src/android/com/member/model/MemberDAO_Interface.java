package android.com.member.model;

import com.member.model.MemberVO;

public interface MemberDAO_Interface {
    MemberVO findByEmailAndPassword(String email, String password);
}
