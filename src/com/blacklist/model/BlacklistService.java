package com.blacklist.model;

import java.util.List;

public class BlacklistService {

	private BlacklistDAO_interface dao;

	public BlacklistService() {
		dao = new BlacklistJDBCDAO();
	}

	public BlacklistVO addBlacklist(String m_id, String m_blackId) {

		BlacklistVO blacklistVO = new BlacklistVO();

		blacklistVO.setM_id(m_id);
		blacklistVO.setM_blackId(m_blackId);

		dao.insert(blacklistVO);

		return blacklistVO;
	}

	public BlacklistVO updateBlacklist(String bl_id,String m_id, String m_blackId) {

		BlacklistVO blacklistVO = new BlacklistVO();
		blacklistVO.setBl_id(bl_id);
		blacklistVO.setM_id(m_id);
		blacklistVO.setM_blackId(m_blackId);

		dao.update(blacklistVO);

		return blacklistVO;
	}

	public void deleteBlacklist(String bl_id) {
		dao.delete(bl_id);
	}

	public BlacklistVO getOneBlacklist(String bl_id) {
		return dao.findByPrimaryKey(bl_id);
	}

	public List<BlacklistVO> getAll() {
		return dao.getAll();
	}
//	public static void main(String[] args) {
//		BlacklistService blacklistService= new BlacklistService();
//		blacklistService.updateBlacklist("BL00001", "M00001", "M00003");
//	}
}
