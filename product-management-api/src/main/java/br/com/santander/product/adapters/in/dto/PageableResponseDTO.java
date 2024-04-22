package br.com.santander.product.adapters.in.dto;

import java.util.List;

public class PageableResponseDTO <T> {
	
	private Integer page;
	private Integer size;
	private List<T> listProducts;
	
	
	
	
	public PageableResponseDTO(Integer page, Integer size, List<T> listProducts) {
		super();
		this.page = page;
		this.size = size;
		this.listProducts = listProducts;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public List<T> getListProducts() {
		return listProducts;
	}
	public void setListProducts(List<T> listProducts) {
		this.listProducts = listProducts;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}

}
