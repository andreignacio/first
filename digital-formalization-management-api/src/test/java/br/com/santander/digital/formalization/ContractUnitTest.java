package br.com.santander.digital.formalization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import br.com.santander.digital.formalization.adapters.in.converter.ContractConverter;
import br.com.santander.digital.formalization.adapters.in.dto.ClientResponseDTO;
import br.com.santander.digital.formalization.adapters.in.dto.FormaliationContractRequestDTO;
import br.com.santander.digital.formalization.adapters.in.dto.ProductResponseDTO;
import br.com.santander.digital.formalization.adapters.out.ContractAdapter;
import br.com.santander.digital.formalization.adapters.out.repository.ContractRepository;
import br.com.santander.digital.formalization.adapters.out.rest.ClientService;
import br.com.santander.digital.formalization.adapters.out.rest.ProductService;
import br.com.santander.digital.formalization.application.core.domain.Contract;
import br.com.santander.digital.formalization.application.core.exception.BusinessException;
import br.com.santander.digital.formalization.util.BeanCreatorForTest;



@ExtendWith(MockitoExtension.class)
public class ContractUnitTest {
	
	@InjectMocks
	private ContractAdapter contractAdpater;
	
	@Mock
    private ClientService clientService;
    
    @Mock
    private ProductService productService;
    
    @Mock
    private ContractRepository repository;
    
    @Mock
    private ContractConverter converter;
	
    
	@Test
	public void testCreateContract() {
		
		FormaliationContractRequestDTO requestDTO = BeanCreatorForTest.getRequestDTO();
		
		when(productService.getProduct(anyString())).thenReturn(ResponseEntity.ok(BeanCreatorForTest.getProductResponseDTO()));
		when(clientService.getClient(anyString())).thenReturn(ResponseEntity.ok(BeanCreatorForTest.getClientResponseDTO()));
		when(repository.save(any())).thenReturn(BeanCreatorForTest.getContractEntity());
		when(converter.fromRepositoryEntityToModelCore(any())).thenReturn(BeanCreatorForTest.getContract());
		
		Contract savedContract = contractAdpater.saveContract(requestDTO.getClientId(), requestDTO.getProductId());
		
		assertThat(savedContract.getId() != null);
		
	}
	
	@Test
	public void testCreateContractInvalidClient() {
		
		FormaliationContractRequestDTO requestDTO = BeanCreatorForTest.getRequestDTO();
		when(productService.getProduct(anyString())).thenReturn(ResponseEntity.ok(BeanCreatorForTest.getProductResponseDTO()));
		ResponseEntity<ClientResponseDTO> responseEntity =  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		when(clientService.getClient(anyString())).thenReturn(responseEntity);
		
		assertThrows(BusinessException.class, () -> contractAdpater.saveContract(requestDTO.getClientId(), requestDTO.getProductId()));
		
	}
	
	
	@Test
	public void testCreateContractInvalidProduct() {
		
		FormaliationContractRequestDTO requestDTO = BeanCreatorForTest.getRequestDTO();
		ResponseEntity<ProductResponseDTO> responseEntity =  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		
		when(productService.getProduct(anyString())).thenReturn(responseEntity);
		
		assertThrows(BusinessException.class, () -> contractAdpater.saveContract(requestDTO.getClientId(), requestDTO.getProductId()));
		
	}
	
	/*@Test
	public void testUpdateClient() {
		ClientEntity clientEntity = BeanCreatorForTest.getClientEntity();
		Client client = BeanCreatorForTest.getClient();
		Client clientToSave = BeanCreatorForTest.getClient();
		
		when(repository.save(any())).thenReturn(clientEntity);
		when(converter.fromCoreDomainModelToRepositoryEntity(any())).thenReturn(clientEntity);
		when(converter.fromRepositoryEntityToCoreDomainModel(any())).thenReturn(client);
		
		Client clientSaved = clientPort.saveClient(clientToSave);
		
		
		assertThat(clientSaved.getId() != null);
		
	}
	
	
	@Test
	public void testUpdateClientFail() {
		Client clientToSave = BeanCreatorForTest.getClient();
		clientToSave.setId(null);
		
		when(repository.findByDocument(anyString())).thenReturn(BeanCreatorForTest.getClientEntity());
		
		assertThrows(BusinessException.class, () -> clientPort.saveClient(clientToSave));
	}
	
	
	@Test
	public void testDeleClient() {
		ClientEntity clientEntity = BeanCreatorForTest.getClientEntity();
		
		when(repository.findById(anyString())).thenReturn(Optional.of(clientEntity));
		
		clientPort.deleteClientById(clientEntity.getId());
		
		verify(repository, times(1)).delete(clientIdCaptor.capture());
		
		ClientEntity capturedClient= clientIdCaptor.getValue();

        assertEquals(clientEntity, capturedClient);
		
	}
	
	
	@Test
	public void testDeleClientFail() {
		ClientEntity clientEntity = BeanCreatorForTest.getClientEntity();
		
		when(repository.findById(anyString())).thenReturn(Optional.empty());
		
		assertThrows(BusinessException.class, () -> clientPort.deleteClientById(clientEntity.getId()));
		
	}
	
	
	@Test
	public void testFindById() {
		ClientEntity clientEntity = BeanCreatorForTest.getClientEntity();
		Client client = BeanCreatorForTest.getClient();
		
		when(repository.findById(anyString())).thenReturn(Optional.of(clientEntity));
		when(converter.fromRepositoryEntityToCoreDomainModel(any())).thenReturn(client);
		
		Client clientRetrieved = clientPort.findById(clientEntity.getId());
		
		assertEquals(clientRetrieved.getId(), clientEntity.getId());
		
	}
	
	
	@Test
	public void testFindByIdFail() {
		ClientEntity clientEntity = BeanCreatorForTest.getClientEntity();
		
		when(repository.findById(anyString())).thenReturn(Optional.empty());
		
		assertThrows(BusinessException.class, () -> clientPort.findById(clientEntity.getId()));
		
	}
*/
}
