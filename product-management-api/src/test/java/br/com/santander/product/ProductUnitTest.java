package br.com.santander.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.santander.product.adapters.in.converter.ProductConverter;
import br.com.santander.product.adapters.in.entity.ProductEntity;
import br.com.santander.product.adapters.out.ProductAdapter;
import br.com.santander.product.adapters.out.repository.ProductRepository;
import br.com.santander.product.application.core.domain.Product;
import br.com.santander.product.application.core.exception.BusinessException;
import br.com.santander.product.ultil.BeanCreatorForTest;

@ExtendWith(MockitoExtension.class)
public class ProductUnitTest {
	
	@InjectMocks
	private ProductAdapter productPort;
	
	@Mock
	private ProductRepository repository;
	@Mock
	private ProductConverter converter;
	
	@Captor
    private ArgumentCaptor<ProductEntity> productIdCaptor;
	
	@Test
	public void testCreateProduct() {
		ProductEntity productEntity = BeanCreatorForTest.getProductEntity();
		Product product = BeanCreatorForTest.getProduct();
		Product productToSave = BeanCreatorForTest.getProduct();
		productToSave.setId(null);
		
		when(repository.save(any())).thenReturn(productEntity);
		when(converter.fromCoreDomainModelToRepositoryEntity(any())).thenReturn(productEntity);
		when(converter.fromRepositoryEntityToCoreDomainModel(any())).thenReturn(product);
		
		Product productSaved = productPort.saveProduct(productToSave);
		
		
		assertThat(productSaved.getId() != null);
		
	}
	
	@Test
	public void testUpdateProduct() {
		ProductEntity productEntity = BeanCreatorForTest.getProductEntity();
		Product product = BeanCreatorForTest.getProduct();
		Product productToSave = BeanCreatorForTest.getProduct();
		
		when(repository.save(any())).thenReturn(productEntity);
		when(repository.findById(anyString())).thenReturn(Optional.of(productEntity));
		when(converter.fromCoreDomainModelToRepositoryEntity(any())).thenReturn(productEntity);
		when(converter.fromRepositoryEntityToCoreDomainModel(any())).thenReturn(product);
		
		Product productSaved = productPort.saveProduct(productToSave);
		
		
		assertThat(productSaved.getId() != null);
		
	}
	
	
	@Test
	public void testUpdateProductFail() {
		Product productToSave = BeanCreatorForTest.getProduct();
		
		when(repository.findById(anyString())).thenReturn(Optional.empty());
		
		assertThrows(BusinessException.class, () -> productPort.saveProduct(productToSave));
	}
	
	
	@Test
	public void testDeleProduct() {
		ProductEntity productEntity = BeanCreatorForTest.getProductEntity();
		
		when(repository.findById(anyString())).thenReturn(Optional.of(productEntity));
		
		productPort.deleteProductById(productEntity.getCode());
		
		verify(repository, times(1)).delete(productIdCaptor.capture());
		
		ProductEntity capturedProduct= productIdCaptor.getValue();

        assertEquals(productEntity, capturedProduct);
		
	}
	
	
	@Test
	public void testDeleProductFail() {
		ProductEntity productEntity = BeanCreatorForTest.getProductEntity();
		
		when(repository.findById(anyString())).thenReturn(Optional.empty());
		
		assertThrows(BusinessException.class, () -> productPort.deleteProductById(productEntity.getCode()));
		
	}
	
	
	@Test
	public void testFindById() {
		ProductEntity productEntity = BeanCreatorForTest.getProductEntity();
		Product product = BeanCreatorForTest.getProduct();
		
		when(repository.findById(anyString())).thenReturn(Optional.of(productEntity));
		when(converter.fromRepositoryEntityToCoreDomainModel(any())).thenReturn(product);
		
		Product productRetrieved = productPort.findById(productEntity.getId());
		
		assertEquals(productRetrieved.getId(), productEntity.getId());
		
	}
	
	
	@Test
	public void testFindByIdFail() {
		ProductEntity productEntity = BeanCreatorForTest.getProductEntity();
		
		when(repository.findById(anyString())).thenReturn(Optional.empty());
		
		assertThrows(BusinessException.class, () -> productPort.findById(productEntity.getId()));
		
	}

}
