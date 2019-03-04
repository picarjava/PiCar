package com.member.model;

import java.util.List;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(String name, String email, String password, String phone, String creditcard, Integer pet,
			Integer smoke, Integer gender, Integer token, Integer activityToken, java.sql.Date birthday,
			Integer verified, Integer babySeat) {

		MemberVO memberVO = new MemberVO();

//		memberVO.setMemID(memID);
		memberVO.setName(name);
		memberVO.setEmail(email);
		memberVO.setPassword(password);
		memberVO.setPhone(phone);
		memberVO.setCreditcard(creditcard);
		memberVO.setPet(pet);
		memberVO.setSmoke(smoke);
		memberVO.setGender(gender);
		memberVO.setToken(token);
		memberVO.setActivityToken(activityToken);
		memberVO.setBirthday(birthday);
		memberVO.setVerified(verified);
		memberVO.setBabySeat(babySeat);
		dao.insert(memberVO);

		return memberVO;
	}

	public MemberVO updateMember(String memID, String name, String email, String password, String phone,
			String creditcard, Integer pet, Integer smoke, Integer gender, Integer token, Integer activityToken,
			java.sql.Date birthday, Integer verified, Integer babySeat) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMemID(memID);
		memberVO.setName(name);
		memberVO.setEmail(email);
		memberVO.setPassword(password);
		memberVO.setPhone(phone);
		memberVO.setCreditcard(creditcard);
		memberVO.setPet(pet);
		memberVO.setSmoke(smoke);
		memberVO.setGender(gender);
		memberVO.setToken(token);
		memberVO.setActivityToken(activityToken);
		memberVO.setBirthday(birthday);
		memberVO.setVerified(verified);
		memberVO.setBabySeat(babySeat);
		dao.update(memberVO);

		return memberVO;
	}

	public MemberVO getOneMember(String memID) {
		return dao.findByPrimaryKey(memID);
	}

	public void deleteEmp(String memID) {
		dao.delete(memID);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}

	public MemberVO getOneMemberByPass(String memID, String password) {
		return dao.findByLoginPass(memID, password);
	}

	public MemberVO updateToken(String memID, Integer token) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMemID(memID);
		memberVO.setToken(token);
		dao.updateToken(memberVO);

		return memberVO;

	}

}
