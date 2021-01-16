package com.member.model;

import java.util.List;
import java.util.Set;

public interface MemberDAO_interface {
	//ï¿½@ï¿½ï¿½ï¿½|ï¿½ï¿½ï¿½sï¿½W
	public void insert (MemberVO memberVO);
	//ï¿½ï¿½ï¿½aï¿½|ï¿½ï¿½ï¿½sï¿½W
	public void insertSeller (MemberVO memberVO);
	//ï¿½×§ï¿½
	public void update (MemberVO memberVO);
	//ï¿½×§ï¿½ï¿½Kï¿½X
	public void updatepw(MemberVO memberVO);

	public void updateStatus(MemberVO memberVO);
	//ï¿½Rï¿½ï¿½
	public void delete(String m_id);
	//ï¿½dï¿½Dkey
	public MemberVO findByPK(String m_id);
	//ï¿½dï¿½ï¿½ï¿½ï¿½
	public List<MemberVO> getAll();
	//ï¿½dï¿½Kï¿½X
	public MemberVO getMemberPw(String m_email);
	//ï¿½dï¿½ß·|ï¿½ï¿½ï¿½ï¿½ï¿½A
	public List<MemberVO> getMemberByStatus(Integer m_status);

	//ï¿½Hï¿½ï¿½ï¿½Ò«H
	public void activeMember(MemberVO memberVO);

	//ï¿½×§ï¿½ï¿½|ï¿½ï¿½Kunï¿½ï¿½
	public void changeKun(MemberVO memberVO);


	//ï¿½qï¿½ï¿½ï¿½`ï¿½ï¿½
	public List<MemberVO> getNotice(String m_id);
	
	//­×§ï½æ³õ­¶­±
	public void updateSellstore(MemberVO memberVO);
	
	//Ajax­×§ïemail
	public void updateEmail(MemberVO memberVO);

}
