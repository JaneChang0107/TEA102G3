package com.productType.model;

import java.util.List;

public interface ProductTypeDAOInterface {

	public void insert(ProductTypeVO productType);
	public void update(ProductTypeVO productType);
	public void delete(String p_id);
	public ProductTypeVO findByPrimaryKey(String p_id);
	public List<ProductTypeVO> getAll();
}
