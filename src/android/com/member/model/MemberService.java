package android.com.member.model;

import com.member.model.MemberVO;

public class MemberService {
    private MemberDAO_Interface memberDAO = new MemberDAO();
    
    public MemberVO getOneByEmailAndPassword(String email, String password) {
        return memberDAO.findByEmailAndPassword(email, password);
    }
}
