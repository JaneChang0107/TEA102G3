package com.member.model;

import java.util.List;
import java.util.Set;

public interface MemberDAO_interface {
	//�@���|���s�W
	public void insert (MemberVO memberVO);
	//���a�|���s�W
	public void insertSeller (MemberVO memberVO);
	//�ק�
	public void update (MemberVO memberVO);
	//�ק��K�X
	public void updatepw(MemberVO memberVO);

	public void updateStatus(MemberVO memberVO);
	//�R��
	public void delete(String m_id);
	//�d�Dkey
	public MemberVO findByPK(String m_id);
	//�d����
	public List<MemberVO> getAll();
	//�d�K�X
	public MemberVO getMemberPw(String m_email);
	//�d�߷|�����A
	public Set<MemberVO> getMemberByStatus(Integer m_status);

	//�H���ҫH
	public void activeMember(MemberVO memberVO);

	//�ק��|��Kun��
	public void changeKun(MemberVO memberVO);


	//�q���`��
	public List<MemberVO> getNotice(String m_id);

}
