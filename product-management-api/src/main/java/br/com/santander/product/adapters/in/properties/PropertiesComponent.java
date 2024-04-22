package br.com.santander.product.adapters.in.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class PropertiesComponent {
	
	@Value("${product.simulation.page.size}")
    private Integer size;
	
	@Value("${product.simulation.code}")
    private String productCode;
	
	
}
