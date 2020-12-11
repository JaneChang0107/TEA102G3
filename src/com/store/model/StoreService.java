package com.store.model;

import java.util.List;

public class StoreService {

	private StoreDAO_interface dao;

	public StoreService() {
		dao = new StoreJDBCDAO();
	}

	public StoreVO addStore(String st_id, String st_name, String st_address) {

		StoreVO storeVO = new StoreVO();

		storeVO.setSt_id(st_id);
		storeVO.setSt_name(st_name);
		storeVO.setSt_address(st_address);

		dao.insert(storeVO);

		return storeVO;
	}

	public StoreVO updateStore(String st_id, String st_name, String st_address) {

		StoreVO storeVO = new StoreVO();

		storeVO.setSt_id(st_id);
		storeVO.setSt_name(st_name);
		storeVO.setSt_address(st_address);

		dao.update(storeVO);

		return storeVO;
	}

	public void deleteStore(String st_id) {
		dao.delete(st_id);
	}

	public StoreVO getOneStore(String st_id) {
		return dao.findByPrimaryKey(st_id);
	}

	public List<StoreVO> getAll() {
		return dao.getAll();
	}
}
