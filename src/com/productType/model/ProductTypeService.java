package com.productType.model;

import java.util.List;

public class ProductTypeService {

	private ProductTypeDAOInterface dao;
	
	public ProductTypeService() {
		dao = new ProductTypeJDBCDAO();
	}
	
	
	public ProductTypeVO addProductType(String platForm, String kind) {
		
		ProductTypeVO ptVO = new ProductTypeVO();
		
		ptVO.setPt_platform(platForm);
		ptVO.setPt_kind(kind);
		dao.insert(ptVO);
		return ptVO;
	}
	
	public void updateProductType(String ptid, String platForm, String kind) {
		
		ProductTypeVO ptVO = new ProductTypeVO();
		
		ptVO.setPt_id(ptid);
		ptVO.setPt_platform(platForm);
		ptVO.setPt_kind(kind);
		
		dao.update(ptVO);
	}
	
	public void deleteProductType(String ptid) {
		dao.delete(ptid);
	}
	
	public ProductTypeVO getOneProductType(String ptid) {
		ProductTypeVO ptVO = dao.findByPrimaryKey(ptid);
		return ptVO;
	}
	
	public List<ProductTypeVO> getAll() {
		return dao.getAll();
	}
	
}
