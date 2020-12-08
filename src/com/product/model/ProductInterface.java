package com.product.model;

import java.util.List;

public interface ProductInterface {

	public void insert(ProductVO product);
	public void update(ProductVO product);
	public void delete(String p_id);
	public List<ProductVO> findByProductName(String p_name);					// 搜尋用
	public List<ProductVO> findByProductName(String p_name, String pt_id);		// 搜尋用
	public List<ProductVO> findByStatus(Integer p_status);						// 審查用
	public List<ProductVO> getAll();
}
