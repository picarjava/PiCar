package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	
	public void insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public void delete(String memID);
	public MemberVO findByPrimaryKey(String memID);
	public List<MemberVO> getAll();
	public MemberVO findByLoginPass(String memID, String password);
	public void updateToken(MemberVO memberVO);
	public void updateVerified(String memID);
	public void setForHobby(MemberVO memberVO);  //阿君新增FOR前端喜好設定
	
	public void updatePassVerified(String memID, String password);
//	萬用複合查詢(傳入參數型別Map)(回傳List)
	
//	public List<MemberVO> getAll(Map<String, String> map);
	
	
}
