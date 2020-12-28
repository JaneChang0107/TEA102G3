package com.member.model;

import java.util.List;
import java.util.Set;


public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberJDBCDAO();
	}

	//一般會員新增
	public MemberVO addMem(String m_email, String m_password, String m_name, String m_gender, String m_phone,
			String m_address, java.sql.Date m_birth) {

		MemberVO memberVO = new MemberVO();

		memberVO.setM_email(m_email);
		memberVO.setM_password(m_password);
		memberVO.setM_name(m_name);
		memberVO.setM_gender(m_gender);
		memberVO.setM_phone(m_phone);
		memberVO.setM_address(m_address);
		memberVO.setM_birth(m_birth);

		dao.insert(memberVO);

		return memberVO;
	}

	//賣家會員新增
	public MemberVO addMem2(String m_email, String m_password, String m_name, String m_gender, String m_phone,
			String m_address, java.sql.Date m_birth, byte[] m_headpic, String m_identity,
			byte[] m_id_pic, String m_account, String m_accountName, String b_code, byte[] m_bank_pic,
			String m_storename, String m_info, byte[] m_cover, String m_hi, String m_offlineHi) {

		MemberVO memberVO = new MemberVO();

		memberVO.setM_email(m_email);
		memberVO.setM_password(m_password);
		memberVO.setM_name(m_name);
		memberVO.setM_gender(m_gender);
		memberVO.setM_phone(m_phone);
		memberVO.setM_address(m_address);
		memberVO.setM_birth(m_birth);
		memberVO.setM_headpic(m_headpic);
		memberVO.setM_identity(m_identity);
		memberVO.setM_id_pic(m_id_pic);
		memberVO.setM_account(m_account);
		memberVO.setM_accountName(m_accountName);
		memberVO.setB_code(b_code);
		memberVO.setM_bank_pic(m_bank_pic);
		memberVO.setM_storename(m_storename);
		memberVO.setM_info(m_info);
		memberVO.setM_cover(m_cover);
		memberVO.setM_hi(m_hi);
		memberVO.setM_offlineHi(m_offlineHi);

		dao.insertSeller(memberVO);

		return memberVO;
	}
		
	
	//修改會員建構子
	public MemberVO updateMem(String m_email, String m_password, String m_name, String m_gender, String m_phone,
			String m_address, java.sql.Date m_birth, byte[] m_headpic, String m_account, String m_accountName, String b_code,
			 String m_id) {

		MemberVO memberVO = new MemberVO();
		memberVO.setM_email(m_email);
		memberVO.setM_password(m_password);
		memberVO.setM_gender(m_gender);
		memberVO.setM_name(m_name);
		memberVO.setM_phone(m_phone);
		memberVO.setM_address(m_address);
		memberVO.setM_birth(m_birth);
		memberVO.setM_headpic(m_headpic);
		memberVO.setM_account(m_account);
		memberVO.setM_accountName(m_accountName);
		memberVO.setB_code(b_code);
		memberVO.setM_id(m_id);
		dao.update(memberVO);
		return memberVO;

	}
	

	//修改會員
	public MemberVO updateMem(String m_email, String m_password, String m_name, String m_gender, String m_phone,
			String m_address, java.sql.Date m_birth, byte[] m_headpic, String m_identity,
			byte[] m_id_pic, String m_account, String m_accountName, String b_code, byte[] m_bank_pic,
			String m_storename, String m_info, byte[] m_cover, String m_hi, String m_offlineHi, String m_id) {

		MemberVO memberVO = new MemberVO();

		memberVO.setM_email(m_email);
		memberVO.setM_password(m_password);
		memberVO.setM_gender(m_gender);
		memberVO.setM_name(m_name);
		memberVO.setM_phone(m_phone);
		memberVO.setM_address(m_address);
		memberVO.setM_birth(m_birth);
		memberVO.setM_headpic(m_headpic);
		memberVO.setM_identity(m_identity);
		memberVO.setM_id_pic(m_id_pic);
		memberVO.setM_account(m_account);
		memberVO.setM_accountName(m_accountName);
		memberVO.setB_code(b_code);
		memberVO.setM_bank_pic(m_bank_pic);
		memberVO.setM_storename(m_storename);
		memberVO.setM_info(m_info);
		memberVO.setM_cover(m_cover);
		memberVO.setM_hi(m_hi);
		memberVO.setM_offlineHi(m_offlineHi);
		memberVO.setM_id(m_id);
		dao.update(memberVO);
		return memberVO;

	}

	public void deleteMem(String m_id) {
		dao.delete(m_id);
	}

	public MemberVO findOneMem(String m_id) {
		return dao.findByPK(m_id);
	}
	
	public List<MemberVO> getAll() {
		return dao.getAll();
	}

	public Set<MemberVO> getMemberByStatus(Integer m_status) {
		return dao.getMemberByStatus(m_status);
	}

	public MemberVO getMemberPw(String m_email) {
		return dao.getMemberPw(m_email);
	}
	
	

}
