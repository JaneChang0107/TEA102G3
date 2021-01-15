package com.product.model;

import java.util.List;

public interface ProductInterface {

	public String insert(ProductVO product);
	public void update(ProductVO product);
	public void checked(String p_id, Integer p_status);							// 審核完成
	public void sellout(String p_id);										// 改上下架
	public void delete(String p_id);
	public void sellerDelete(String p_id);
	public ProductVO findOneProduct (String p_id);
	public List<ProductVO> findBySeller(String m_id);							// 賣家商品列表用
	public List<ProductVO> findByProductName(String p_name);					// 搜尋用
	public List<ProductVO> findByProductType(String pt_id);					// 搜尋用
	public List<ProductVO> findByProductName(String p_name, String pt_id);		// 搜尋用
	public List<ProductVO> findByStatus(Integer p_status);						// 審查用 確認狀態用
	public List<ProductVO> getAllSell();
	public List<ProductVO> getAll();
}
