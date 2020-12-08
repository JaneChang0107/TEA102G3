package com.member.model;

import java.util.List;
import java.util.Set;

public interface MemberDAO_interface {
	public void insert (MemberVO memberVO);
	public void update (MemberVO memberVO);
	public void delete(String m_id);
	public MemberVO findByPK(String m_id);
	public List<MemberVO> getAll();
	public MemberVO getMemberPw(String m_email);
	
	//¬d¸ß·|­ûª¬ºA
	public Set<MemberVO> getMemberByStatus(Integer m_status);
	
}
