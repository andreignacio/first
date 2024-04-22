package br.com.santander.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.santander.product.application.core.service.ProductService;
import br.com.santander.product.application.ports.out.ProductPort;

@Configuration
public class ServiceBeansConfig {
	
	@Bean
	public ProductService generateProductService(ProductPort productPort) {
		return new ProductService(productPort);
	}
	
}
