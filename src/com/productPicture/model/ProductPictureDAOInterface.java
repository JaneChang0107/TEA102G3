package com.productPicture.model;

import java.util.List;

public interface ProductPictureDAOInterface {

	public void insert(ProductPictureVO productPicture);
	public void update(ProductPictureVO productPicture);
	public void delete(String pp_id);
	public ProductPictureVO findByPrimaryKey(String pp_id);
	public List<ProductPictureVO> getAll();
}
