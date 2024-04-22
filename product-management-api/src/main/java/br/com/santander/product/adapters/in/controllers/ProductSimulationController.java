package br.com.santander.product.adapters.in.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.product.adapters.in.converter.ProductConverter;
import br.com.santander.product.adapters.in.dto.PageableResponseDTO;
import br.com.santander.product.adapters.in.dto.ProductSimulationResponseDTO;
import br.com.santander.product.adapters.in.properties.PropertiesComponent;
import br.com.santander.product.application.core.domain.Product;
import br.com.santander.product.application.ports.in.ProductServicePort;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/products/simulator")
@AllArgsConstructor
public class ProductSimulationController {
	
	private final ProductServicePort productServicePort;
	private final ProductConverter converter;
	private final PropertiesComponent propertiesComponent;
	
		
	@GetMapping
    @Operation(summary = "Get simulation of price card for client")
    public PageableResponseDTO<ProductSimulationResponseDTO> simulationCard(@RequestParam(defaultValue = "") String code,
            @RequestParam(defaultValue = "0") int page) {
		
		Integer sizeOfPages = propertiesComponent.getSize();
		List<Product> productList = productServicePort.getSimulationCardProduct(propertiesComponent.getProductCode(), Integer.valueOf(page), sizeOfPages);
		
		return new PageableResponseDTO<ProductSimulationResponseDTO>(Integer.valueOf(page), sizeOfPages, 
				productList.stream().map(converter::fromCoreDomainModelToSimulationResponseDTO).collect(Collectors.toList()));
		
    }

  

}
