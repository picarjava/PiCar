package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	
	public void insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public void delete(MemberVO memberVO);
	public MemberVO findByPrimaryKey(String memID);
	public List<MemberVO> getAll();
	
//	萬用複合查詢(傳入參數型別Map)(回傳List)
	
//	public List<MemberVO> getAll(Map<String, String> map);
	
	
}
