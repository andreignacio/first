package br.com.santander.product.adapters.out.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.santander.product.adapters.in.entity.ProductEntity;


public interface ProductRepository extends MongoRepository<ProductEntity, String> {
		
	Page<ProductEntity> findByCode(String code, Pageable pageable);
	
	

}
