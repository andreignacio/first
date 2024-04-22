package br.com.santander.product.adapters.out;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import br.com.santander.product.adapters.in.converter.ProductConverter;
import br.com.santander.product.adapters.in.entity.ProductEntity;
import br.com.santander.product.adapters.out.repository.ProductRepository;
import br.com.santander.product.application.core.domain.Product;
import br.com.santander.product.application.core.exception.BusinessException;
import br.com.santander.product.application.ports.out.ProductPort;
import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class ProductAdapter implements ProductPort {
	
	private static final String NÃO_ENCONTRADO_PRODUTO_PARA_O_CODIGO_INFORMADO = "Não encontrado produto para o codigo informado";
	private final ProductRepository repository;
	private final ProductConverter converter;
	

	@Override
	public Product saveProduct(Product product) throws BusinessException{
		
		if (!ObjectUtils.isEmpty(product.getId())) {
			Optional<ProductEntity> optionalProduct = repository.findById(product.getId());
			if(optionalProduct.isEmpty())
				throw new BusinessException(NÃO_ENCONTRADO_PRODUTO_PARA_O_CODIGO_INFORMADO); 
            
		}
		
		ProductEntity productEntity = converter.fromCoreDomainModelToRepositoryEntity(product);
		ProductEntity save = repository.save(productEntity);
		return converter.fromRepositoryEntityToCoreDomainModel(save);
	}

	@Override
	public void deleteProductById(String code) {
		ProductEntity productEntity = getProductById(code);
		
		repository.delete(productEntity);
	}

	private ProductEntity getProductById(String id) {
		Optional<ProductEntity> productEntity = repository.findById(id);
		
		if (productEntity.isEmpty()) {
			throw new BusinessException(NÃO_ENCONTRADO_PRODUTO_PARA_O_CODIGO_INFORMADO);
		}
		return productEntity.get();
	}

	@Override
	public Product findById(String id) {
		
		ProductEntity productEntity = getProductById(id);
		
		return converter.fromRepositoryEntityToCoreDomainModel(productEntity);
	}

	@Override
	public List<Product> findAll() {
		return repository.findAll().
			stream().map(product -> converter.fromRepositoryEntityToCoreDomainModel(product)).
			collect(Collectors.toList());
	}

	@Override
	public List<Product> getSimulationCardProduct(String productCode, Integer page, Integer size) {
		
		Pageable pageable =  PageRequest.of(page, size);
		
		Page<ProductEntity> pageResponse = repository.findByCode(productCode,  pageable);
		
		if (pageResponse.isEmpty()) {
			throw new BusinessException(NÃO_ENCONTRADO_PRODUTO_PARA_O_CODIGO_INFORMADO);
		}
		
		return pageResponse.get().map(product -> converter.fromRepositoryEntityToCoreDomainModel(product)).
				collect(Collectors.toList());
		
	}

	

}
