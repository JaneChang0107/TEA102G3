package com.member.model;

import java.util.List;
import java.util.Set;
import com.member.model.MemberVO;

public class MemberService {
	
	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberJDBCDAO();
	}
	public List<MemberVO> getAll(){
		return dao.getAll();
	}
	
	public Set<MemberVO> getMemberByStatus(Integer m_status){
		return dao.getMemberByStatus(m_status);
	}	
	
	public MemberVO getMemberPw(String m_email) {
		return dao.getMemberPw(m_email);
		
	}

}
