package br.com.santander.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.santander.product.adapters.in.controllers.ProductController;
import br.com.santander.product.adapters.in.converter.ProductConverter;
import br.com.santander.product.adapters.in.dto.ProductRequestDTO;
import br.com.santander.product.adapters.out.repository.ProductRepository;
import br.com.santander.product.application.ports.in.ProductServicePort;
import br.com.santander.product.ultil.BeanCreatorForTest;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
	
	private static final String GIANT_STRING = "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"
			+ "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"
			+ "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm";


	private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;
    
    @InjectMocks
    private ProductController productController;
    
    @Mock
    private ProductServicePort productServicePort;
   
    @Mock
	private ProductConverter converter;
    
    @Mock
	private ProductRepository productRepository;
    
    @Autowired
    private ProductRepository repository;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }
    
    @Test
    @DisplayName("Create a product when a valid product request is provided should return 201 Created")
    public void testCreateProduct() throws Exception {
    	final ProductRequestDTO productRequestDTO = BeanCreatorForTest.getRequestBean();
    	
    	when(converter.fromDTOToCoreDomainModel(any(), any())).thenReturn(BeanCreatorForTest.getProduct());

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // Verifica se o produto foi criado no banco de dados
        assertThat(repository.findAll().size() > 0);
    }

    
    @Test
    @DisplayName("Update a product when a valid product request is provided should return 200 ok")
    public void testUpdateProduct() throws Exception {
    	final ProductRequestDTO productRequestDTO = BeanCreatorForTest.getRequestBean();
    	
    	when(productRepository.findById(anyString())).thenReturn(Optional.of(BeanCreatorForTest.getProductEntity()));

        mockMvc.perform(MockMvcRequestBuilders.put("/products/6623edd9aabc741c9d47ed42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verifica se o produto foi alteado no banco de dados
        assertThat(repository.findById("6623edd9aabc741c9d47ed42").isPresent());
        assertThat(repository.findAll().size() > 0);
    }
    
    @Test
    @DisplayName("Retrieve all products")
    public void testFindAll() throws Exception {
    	
    	when(productServicePort.findAll()).thenReturn(Collections.singletonList(BeanCreatorForTest.getProduct()));
    	
    	when(converter.fromCoreDomainModelToResponseDTO(any())).thenReturn(BeanCreatorForTest.getResponseBean());
        
        mockMvc.perform(get("/products"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].productName").value("Cartão de Credito"))
	        .andExpect(jsonPath("$[0].productDescription").value("Cartão Visa Ouro"))
	        .andExpect(MockMvcResultMatchers.status().isOk());;

    }
    
    @Test
    @DisplayName("Retrieve one product")
    public void testFindbyId() throws Exception {
    	
    	when(productServicePort.findById(anyString())).thenReturn(BeanCreatorForTest.getProduct());
    	
    	when(converter.fromCoreDomainModelToResponseDTO(any())).thenReturn(BeanCreatorForTest.getResponseBean());
        
        mockMvc.perform(get("/products/6623edd9aabc741c9d47ed42"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.productName").value("Cartão de Credito"))
	        .andExpect(jsonPath("$.productDescription").value("Cartão Visa Ouro"))
	        .andExpect(MockMvcResultMatchers.status().isOk());;

    }
    
    
    @Test
    @DisplayName("Create product when name is invalid should return 400 Bad Request")
    void testCreateWithInvalidName() throws Exception {
    	final ProductRequestDTO productRequestDTO = BeanCreatorForTest.getRequestBean();
    	productRequestDTO.setProductName(null);

    	 mockMvc.perform(MockMvcRequestBuilders.post("/products")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(productRequestDTO)))
                 .andExpect(status().isBadRequest());
    	 
    	 productRequestDTO.setProductName(GIANT_STRING);
    	 mockMvc.perform(MockMvcRequestBuilders.post("/products")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(productRequestDTO)))
                 .andExpect(status().isBadRequest());

    }
    
    @Test
    @DisplayName("Create product when code is invalid should return 400 Bad Request")
    void testCreateWithInvalidCode() throws Exception {
    	final ProductRequestDTO productRequestDTO = BeanCreatorForTest.getRequestBean();
    	productRequestDTO.setProductCode(null);

    	 mockMvc.perform(MockMvcRequestBuilders.post("/products")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(productRequestDTO)))
                 .andExpect(status().isBadRequest());
    	 
    	 productRequestDTO.setProductCode(GIANT_STRING);
    	 mockMvc.perform(MockMvcRequestBuilders.post("/products")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(productRequestDTO)))
                 .andExpect(status().isBadRequest());

    }
    
    @Test
    @DisplayName("Create description when code is invalid should return 400 Bad Request")
    void testCreateWithInvalidDescription() throws Exception {
    	final ProductRequestDTO productRequestDTO = BeanCreatorForTest.getRequestBean();
    	productRequestDTO.setProductDescription(null);

    	 mockMvc.perform(MockMvcRequestBuilders.post("/products")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(productRequestDTO)))
                 .andExpect(status().isBadRequest());
    	 
    	 productRequestDTO.setProductDescription(GIANT_STRING);
    	 mockMvc.perform(MockMvcRequestBuilders.post("/products")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(productRequestDTO)))
                 .andExpect(status().isBadRequest());

    }
    
    @Test
    @DisplayName("Create description when code is invalid should return 400 Bad Request")
    void testCreateWithInvalidPrice() throws Exception {
    	final ProductRequestDTO productRequestDTO = BeanCreatorForTest.getRequestBean();
    	productRequestDTO.setProductPrice(null);

    	 mockMvc.perform(MockMvcRequestBuilders.post("/products")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(productRequestDTO)))
                 .andExpect(status().isBadRequest());
    	 
    	 productRequestDTO.setProductPrice(new BigDecimal(0));

    	 mockMvc.perform(MockMvcRequestBuilders.post("/products")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(productRequestDTO)))
                 .andExpect(status().isBadRequest());
    	 
    	 productRequestDTO.setProductPrice(new BigDecimal(10000000));
    	 mockMvc.perform(MockMvcRequestBuilders.post("/products")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(productRequestDTO)))
                 .andExpect(status().isBadRequest());

    }
    

}
