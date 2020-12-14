package com.product.model;

import java.util.List;

public interface ProductInterface {

	public void insert(ProductVO product);
	public void update(ProductVO product);
	public void delete(String p_id);
	public ProductVO findOneProduct (String p_id);
	public List<ProductVO> findByProductName(String p_name);					// �j�M��
	public List<ProductVO> findByProductName(String p_name, String pt_id);		// �j�M��
	public List<ProductVO> findByStatus(Integer p_status);						// �f�d��
	public List<ProductVO> getAll();
}
