package com.productPicture.model;

import java.util.List;

public class ProductPictureService {

	private ProductPictureDAOInterface dao;
	
	public ProductPictureService() {
		dao = new ProductPictureJDBCDAO();
	}
	
	public ProductPictureVO addProductPicture(byte[] picture, String pid) {
		ProductPictureVO ppVO = new ProductPictureVO();
		ppVO.setPp_picture(picture);
		ppVO.setP_id(pid);
		dao.insert(ppVO);
		return ppVO;
	}
	
	public void updateProductPocture(String ppid, byte[] picture) {
		ProductPictureVO ppVO = new ProductPictureVO();
		ppVO.setPp_id(ppid);
		ppVO.setPp_picture(picture);
		dao.update(ppVO);
	}
	
	public void deleteProductPicture(String ppid) {
		dao.delete(ppid);
	}
	
	public void deleteProductPictureByProduct(String pid) {
		dao.deleteByProduct(pid);
	}
	
	public ProductPictureVO findOneProductPicture(String ppid) {
		return dao.findByPrimaryKey(ppid);
	}
	
	public List<ProductPictureVO> findProductPicture(String pid) {
		return dao.findByProduct(pid);
	}
	
	//測試抓取第一張用----------------
	public ProductPictureVO findFirstOneProductPicture(String pid) {
		ProductPictureVO productureVO = new ProductPictureVO();
		productureVO= (ProductPictureVO)dao.findByProduct(pid).get(0);
		
		return productureVO;
	}
	//-------------------------------
	
	
	public String findProductRandomPicture(String pid) {
		List<ProductPictureVO> ppVOs = dao.findByProduct(pid);
		if(ppVOs.size() > 0 ) {
			int randnum = (int) (Math.random() * ppVOs.size());
			
			return ppVOs.get(randnum).getPp_id();
		}
			return "";
	}
	
	public List<ProductPictureVO> getAll() {
		return dao.getAll();
	}
}
