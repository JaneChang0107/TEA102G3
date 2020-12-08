package com.product.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ProductService {

	private ProductInterface dao;
	
	public ProductService() {
		dao = new ProductJDBCDAO();
	}
	
	public static void main(String[] args) {
		ProductService ps = new ProductService();
		Date d = new Date();
		Timestamp st = new Timestamp(d.getTime());
//		ps.addProduct("bfbre", 1000, "cu cucisniegneingdgoneiw", "PT00011", 1, st, 0, "1");
//		ps.updateProduct("P00021", "vv", 500, "Idontknow", "PT00011", 2, st, 0);
//		ps.deleteProduct("P00021");
		List<ProductVO> list = ps.findProduct("動物森友會");
//		List<ProductVO> list = ps.findProduct("nngded", "PT00011");
//		List<ProductVO> list = ps.getAll();
	
		
		for(ProductVO pvo : list) {
			System.out.println("pid:" + pvo.getP_id());
			System.out.println("pname:" + pvo.getP_name());
			System.out.println("ptid:" + pvo.getPt_id());
			System.out.println("===========================");
		}
		
	}
	
	
	public ProductVO addProduct(String pName, Integer price, String detail, String ptId, Integer count, Timestamp addDate, Integer status, String mId) {
		
		ProductVO pVO = new ProductVO();
		
		pVO.setP_name(pName);
		pVO.setP_price(price);
		pVO.setP_detail(detail);
		pVO.setPt_id(ptId);
		pVO.setP_count(count);
		pVO.setP_addDate(addDate);
		pVO.setP_status(status);
		pVO.setM_id(mId);
		
		dao.insert(pVO);
		
		return pVO;
	}
	
	public ProductVO updateProduct(String pId, String pName, Integer price, String detail, String ptId, Integer count, Timestamp reviseDate, Integer status) {
		
		ProductVO pVO = new ProductVO();
		
		pVO.setP_id(pId);
		pVO.setP_name(pName);
		pVO.setP_price(price);
		pVO.setP_detail(detail);
		pVO.setPt_id(ptId);
		pVO.setP_count(count);
		pVO.setP_reviseDate(reviseDate);
		pVO.setP_status(status);
		
		dao.update(pVO);
		
		return pVO;
	}

	public void deleteProduct(String pId) {
		dao.delete(pId);
	}
	
	public List<ProductVO> findProduct(String name) {
		return dao.findByProductName(name);
	}
	
	public List<ProductVO> findProduct(String name, String ptId) {
		return dao.findByProductName(name, ptId);
	}
	
	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
}
