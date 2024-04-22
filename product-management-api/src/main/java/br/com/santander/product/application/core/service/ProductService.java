package br.com.santander.product.application.core.service;

import java.util.List;

import br.com.santander.product.application.core.domain.Product;
import br.com.santander.product.application.ports.in.ProductServicePort;
import br.com.santander.product.application.ports.out.ProductPort;

public class ProductService implements ProductServicePort {

	private final ProductPort productPort;
	
	public ProductService(ProductPort productPort) {
		this.productPort = productPort;
	}
	
	public Product saveProduct(Product product) {		
		
		Product productSaved = productPort.saveProduct(product);		
		product.setId(productSaved.getId());
		return product;
	}

	@Override
	public Product findById(String id) {
		return productPort.findById(id);
	}

	@Override
	public void deleteById(String id) {
		productPort.deleteProductById(id);
		
	}

	@Override
	public List<Product> findAll() {
		return productPort.findAll();
	}

	@Override
	public List<Product> getSimulationCardProduct(String productCode, Integer page, Integer size) {
		
		return productPort.getSimulationCardProduct(productCode, page, size);
	}

}
