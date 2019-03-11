package android.com.member.model;

import com.member.model.MemberVO;

public class MemberService {
    private MemberDAO_Interface memberDAO = new MemberDAO();
    
    public MemberVO getOneByEmailAndPassword(String email, String password) {
        return memberDAO.findByEmailAndPassword(email, password);
    }
    
    public byte[] getPictureByMemID(String memID) {
        return memberDAO.getPictureByMemID(memID);
    }
    
    public void updatePrefenceByMemID(Integer pet, Integer smoke, Integer babySeat, String memID) {
        memberDAO.updatePreferenceByMemID(pet, smoke, babySeat, memID);
    }
    
    public void updateCreditCardByMemID(String creditCard, String memID) {
        memberDAO.updateCreditCardByMemID(creditCard, memID);
    }
}
