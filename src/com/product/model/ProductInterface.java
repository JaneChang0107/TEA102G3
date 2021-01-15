package com.product.model;

import java.util.List;

public interface ProductInterface {

	public String insert(ProductVO product);
	public void update(ProductVO product);
	public void checked(String p_id, Integer p_status);							// �f�֧���
	public void sellout(String p_id);										// ��W�U�[
	public void delete(String p_id);
	public void sellerDelete(String p_id);
	public ProductVO findOneProduct (String p_id);
	public List<ProductVO> findBySeller(String m_id);							// ��a�ӫ~�C���
	public List<ProductVO> findByProductName(String p_name);					// �j�M��
	public List<ProductVO> findByProductType(String pt_id);					// �j�M��
	public List<ProductVO> findByProductName(String p_name, String pt_id);		// �j�M��
	public List<ProductVO> findByStatus(Integer p_status);						// �f�d�� �T�{���A��
	public List<ProductVO> getAllSell();
	public List<ProductVO> getAll();
}
