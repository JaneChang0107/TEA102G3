package com.viewseller.model;

import java.util.List;


public interface ViewsellerDAO_interface {
	public void insert(ViewsellerVO viewsellerVO);
	public void update(ViewsellerVO viewsellerVO);
	public void delete(String viewsellerVO);
	public ViewsellerVO findByPrimaryKey(String viewsellerVO);
    public List<ViewsellerVO> getAll();
}
