package br.com.santander.product.application.ports.out;

import java.util.List;

import br.com.santander.product.application.core.domain.Product;

public interface ProductPort {
	
	
	Product saveProduct(Product product);
	void deleteProductById(String id);
	Product findById(String id);
	List<Product> findAll();
	List<Product> getSimulationCardProduct(String productCode, Integer page, Integer size);
	

}
