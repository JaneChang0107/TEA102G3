package com.product.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductService {

	private ProductInterface dao;
	
	public ProductService() {
		dao = new ProductJDBCDAO();
//		dao = new ProductJNDIDAO();
	}
	
	public static void main(String[] args) {
		ProductService ps = new ProductService();
//		Date d = new Date();
//		Timestamp st = new Timestamp(d.getTime());
//		ProductVO pVO = ps.addProduct("bfbre", 1000, "cu cucisniegneingdgoneiw", "PT00001", 1, st, 0, "M00001");
//		ps.updateProduct("P00021", "vv", 500, "Idontknow", "PT00011", 2, st, 0);
//		ps.deleteProduct("P00021");
//		List<ProductVO> list = ps.findProduct("¨ë«È");
//		List<ProductVO> list = ps.findProduct("°Êª«", "PT00011");
		List<ProductVO> list = ps.getAll();
	
		ObjectMapper mapper = new ObjectMapper();
		try {
			String a = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
			
			System.out.println(a);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println("pid" + pVO.getP_id());
//		for(ProductVO pvo : list) {
//			System.out.println("pid:" + pvo.getP_id());
//			System.out.println("pname:" + pvo.getP_name());
//			System.out.println("ptid:" + pvo.getPt_id());
//			System.out.println("===========================");
//		}
		
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
		
		String pid = dao.insert(pVO);
		pVO.setP_id(pid);;
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
	
	public void checked(String pid, Integer pstatus) {
		dao.checked(pid, pstatus);
	}
	
	public void sellout(String pid) {
		dao.sellout(pid);
	}

	public void deleteProduct(String pid) {
		dao.delete(pid);
	}
	public void sellerDeleteProduct(String pid) {
		dao.sellerDelete(pid);
	}
	
	public ProductVO oneProduct(String pid) {
		return dao.findOneProduct(pid);
	}
	
	public List<ProductVO> findBySeller(String mid) {
		return dao.findBySeller(mid);
	}
	
	public List<ProductVO> findProduct(String name) {
		return dao.findByProductName(name);
	}
	
	public List<ProductVO> findTypeProduct(String ptid) {
		return dao.findByProductType(ptid);
	}
	
	public List<ProductVO> findProduct(String name, String ptid) {
		return dao.findByProductName(name, ptid);
	}
	
	public List<ProductVO> findByStatus(Integer pstatus) {
		return dao.findByStatus(pstatus);
	}
	
	public List<ProductVO> getAllSell() {
		return dao.getAllSell();
	}
	
	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
}
