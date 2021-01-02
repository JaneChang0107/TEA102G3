package com.member.model;

import java.util.List;
import java.util.Set;

public interface MemberDAO_interface {
	//一般會員新增
	public void insert (MemberVO memberVO);
	//賣家會員新增
	public void insertSeller (MemberVO memberVO);
	//修改
	public void update (MemberVO memberVO);
	//修改密碼
	public void updatepw(MemberVO memberVO);
	
	//刪除
	public void delete(String m_id);
	//查主key
	public MemberVO findByPK(String m_id);
	//查全部
	public List<MemberVO> getAll();
	//查密碼
	public MemberVO getMemberPw(String m_email);
	//查詢會員狀態
	public Set<MemberVO> getMemberByStatus(Integer m_status);
	
	//寄驗證信
	public void activeMember(MemberVO memberVO);
	
}
