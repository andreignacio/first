package br.com.santander.product.adapters.in.controllers;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.product.adapters.in.converter.ProductConverter;
import br.com.santander.product.adapters.in.dto.ProductRequestDTO;
import br.com.santander.product.adapters.in.dto.ProductResponseDTO;
import br.com.santander.product.application.core.domain.Product;
import br.com.santander.product.application.ports.in.ProductServicePort;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
	
	private final ProductServicePort productServicePort;
	private final ProductConverter converter;
	
	@PostMapping
	@Operation(summary = "Create a product")
	public ResponseEntity<Product> saveProduct( @Validated @RequestBody ProductRequestDTO dto) {
		
		Product savedProduct = productServicePort.saveProduct(converter.fromDTOToCoreDomainModel(dto, null));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
		
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Update a product")
	public ResponseEntity<Product> updateProduct( @PathVariable(value = "id") String id, @Validated @RequestBody ProductRequestDTO dto) {
		
		Product savedProduct = productServicePort.saveProduct(converter.fromDTOToCoreDomainModel(dto, id));
		
		return ResponseEntity.status(HttpStatus.OK).body(savedProduct);
		
	}
		
	@GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<List<ProductResponseDTO>> findAll() {

		List<ProductResponseDTO> listProductResponseDTO = productServicePort.findAll().stream().map(converter::fromCoreDomainModelToResponseDTO).toList();
		
		return ResponseEntity.status(HttpStatus.OK).body(listProductResponseDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an product by id")
    public ResponseEntity<ProductResponseDTO> findOne(@PathVariable String id) {
    	
    	ProductResponseDTO productResponseDTO = converter.fromCoreDomainModelToResponseDTO(productServicePort.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }
	
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an product by id")
    public ResponseEntity<Void> delete(@PathVariable String id) {
    	return ResponseEntity.noContent().build();
    }
   

}
