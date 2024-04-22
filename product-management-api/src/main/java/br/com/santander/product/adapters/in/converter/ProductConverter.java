package br.com.santander.product.adapters.in.converter;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import br.com.santander.product.adapters.in.dto.ProductRequestDTO;
import br.com.santander.product.adapters.in.dto.ProductResponseDTO;
import br.com.santander.product.adapters.in.dto.ProductSimulationResponseDTO;
import br.com.santander.product.adapters.in.entity.ProductEntity;
import br.com.santander.product.application.core.domain.Product;

@Component
public class ProductConverter {

	public ProductEntity fromCoreDomainModelToRepositoryEntity(Product product) {
		return ProductEntity.builder()
			.code(product.getCode())
			.description(product.getDescription())
			.id(!ObjectUtils.isEmpty(product.getId()) ? product.getId() : null)
			.name(product.getName())
			.price(product.getPrice())
			.createdDate(product.getCreatedDate())
			.build();
	}
	
	public Product fromRepositoryEntityToCoreDomainModel(ProductEntity productEntity) {
		return new Product.Builder()
			.code(productEntity.getCode())
			.description(productEntity.getDescription())
			.id(!ObjectUtils.isEmpty(productEntity.getId()) ? productEntity.getId() : null)
			.name(productEntity.getName())
			.price(productEntity.getPrice())
			.createdDate(productEntity.getCreatedDate())
			.build();
	}
	
	public Product fromDTOToCoreDomainModel(ProductRequestDTO dto, String id) {
		
		return new Product.Builder()
			.code(dto.getProductCode())
			.description(dto.getProductDescription())
			.id(!ObjectUtils.isEmpty(id) ? id : null)
			.name(dto.getProductName())
			.createdDate(LocalDate.now())
			.price(dto.getProductPrice())
			.build();
		
	}
	
	public ProductResponseDTO fromCoreDomainModelToResponseDTO(Product product) {
		
		return ProductResponseDTO.builder()
				.productCode(product.getCode())
				.productDescription(product.getDescription())
				.productId(!ObjectUtils.isEmpty(product.getId()) ? product.getId() : null)
				.productName(product.getName())
				.productPrice(product.getPrice())
				.productCreatedDate(product.getCreatedDate())
				.build();
	}
	
	public ProductSimulationResponseDTO fromCoreDomainModelToSimulationResponseDTO(Product product) {
		return ProductSimulationResponseDTO.builder()
				.productDescription(product.getDescription())
				.productName(product.getName())
				.productPrice(product.getPrice())
				.build();
	}
	
}


