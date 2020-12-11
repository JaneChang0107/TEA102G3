package com.bankcode.model;

import java.util.List;

public class BankcodeService {
	
	private BankcodeDAO_interface dao;
	
	public BankcodeService() {
		dao = new BankcodeJDBCDAO();
	}
	
	public BankcodeVO addBankcode(String b_code,String b_name) {
	
		BankcodeVO bankcodeVO = new BankcodeVO();

		bankcodeVO.setB_code(b_code);
		bankcodeVO.setB_name(b_name);
	
		dao.insert(bankcodeVO);

		return bankcodeVO;
	}

	public BankcodeVO updateBankcode(String b_code,String b_name) {

		BankcodeVO bankcodeVO = new BankcodeVO();

		bankcodeVO.setB_code(b_code);
		bankcodeVO.setB_name(b_name);
		dao.update(bankcodeVO);

		return bankcodeVO;
	}

	public void deleteBankcode(String b_code) {
		dao.delete(b_code);
	}

	public BankcodeVO getOneBankcode(String b_code) {
		return dao.findByPrimaryKey(b_code);
	}

	public List<BankcodeVO> getAll() {
		return dao.getAll();
	}
}
