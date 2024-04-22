package br.com.santander.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.santander.product.adapters.in.converter.ProductConverter;
import br.com.santander.product.adapters.in.entity.ProductEntity;
import br.com.santander.product.adapters.out.ProductAdapter;
import br.com.santander.product.adapters.out.repository.ProductRepository;
import br.com.santander.product.application.core.domain.Product;
import br.com.santander.product.application.core.exception.BusinessException;
import br.com.santander.product.ultil.BeanCreatorForTest;

@ExtendWith(MockitoExtension.class)
public class ProductSimulationUnitTest {
	
	@InjectMocks
	private ProductAdapter productPort;
	
	@Mock
	private ProductRepository repository;
	@Mock
	private ProductConverter converter;
	
	@Captor
    private ArgumentCaptor<ProductEntity> productIdCaptor;
	
	
	
	@Test
	public void testGetSimulationsCard() {
		Product product = BeanCreatorForTest.getProduct();
		
		Pageable pageable =  PageRequest.of(0, 3);
		List<ProductEntity> productList = Collections.singletonList(BeanCreatorForTest.getProductEntity());
		Page<ProductEntity> page = new PageImpl<>(productList, pageable, productList.size());
		
		when(repository.findByCode(anyString(), any())).thenReturn(page);
		when(converter.fromRepositoryEntityToCoreDomainModel(any())).thenReturn(product);
		
		List<Product> simulationCardProduct = productPort.getSimulationCardProduct("", 0, 3);
		
		assertThat(!simulationCardProduct.isEmpty());
		assertThat(simulationCardProduct.get(0).getCode().equals("CARD"));
		assertThat(simulationCardProduct.get(0).getId().equals("6623edd9aabc741c9d47ed42"));
		
	}
	
	@Test
	public void testGetSimulationsCardFail() {
		
		Pageable pageable =  PageRequest.of(0, 3);
		List<ProductEntity> productList = Collections.emptyList();
		Page<ProductEntity> page = new PageImpl<>(productList, pageable, productList.size());
		
		when(repository.findByCode(anyString(), any())).thenReturn(page);
		
		assertThrows(BusinessException.class, () -> productPort.getSimulationCardProduct("", 0, 3));
		
	}
	
	

}
