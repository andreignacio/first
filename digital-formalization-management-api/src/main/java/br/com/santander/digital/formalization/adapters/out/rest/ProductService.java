package br.com.santander.digital.formalization.adapters.out.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.santander.digital.formalization.adapters.in.dto.ProductResponseDTO;


@FeignClient(name = "products", url = "${product.server.url}")
public interface ProductService {

	
	 @GetMapping( "/products/{id}")
	 ResponseEntity<ProductResponseDTO> getProduct(@PathVariable("id") String id);
}
