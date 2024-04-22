package br.com.santander.product.application.ports.in;

import java.util.List;

import br.com.santander.product.application.core.domain.Product;

public interface ProductServicePort {
	
	Product saveProduct(Product product);
	Product findById(String id);
	void deleteById(String id);
	List<Product> findAll();
	List<Product> getSimulationCardProduct(String productCode, Integer page, Integer size);
	

}
