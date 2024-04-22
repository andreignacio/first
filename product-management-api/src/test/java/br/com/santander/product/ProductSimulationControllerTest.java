package br.com.santander.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.santander.product.adapters.in.controllers.ProductSimulationController;
import br.com.santander.product.adapters.in.converter.ProductConverter;
import br.com.santander.product.adapters.in.properties.PropertiesComponent;
import br.com.santander.product.application.ports.in.ProductServicePort;
import br.com.santander.product.ultil.BeanCreatorForTest;

@SpringBootTest
@AutoConfigureMockMvc
class ProductSimulationControllerTest {
	

    private MockMvc mockMvc;
    
    @InjectMocks
    private ProductSimulationController productSimulationController;
    
    @Mock
    private ProductServicePort productServicePort;
   
    @Mock
	private ProductConverter converter;
    
    @Mock
    private PropertiesComponent propertiesComponent;
    
    

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productSimulationController).build();
    }
    
    
    @Test
    @DisplayName("Retrieve all Simulation products")
    public void testSimulationCardProducts() throws Exception {
    	
    	when(productServicePort.getSimulationCardProduct(anyString(), anyInt(), anyInt())).thenReturn(Collections.singletonList(BeanCreatorForTest.getProduct()));
    	
    	when(converter.fromCoreDomainModelToSimulationResponseDTO(any())).thenReturn(BeanCreatorForTest.getProductSimulationResponseDTO());
    	when(propertiesComponent.getProductCode()).thenReturn("CARD");
    	when(propertiesComponent.getSize()).thenReturn(3);
        
        mockMvc.perform(get("/products/simulator"))
	        .andExpect(status().isOk())
	        //.andExpect(jsonPath("$.productName").value("Cartão de Credito"))
	        .andExpect(jsonPath("$.listProducts[0].productName").value("Cartão de Credito"))
	        .andExpect(jsonPath("$.listProducts[0].productDescription").value("Cartão Visa Ouro"))
	        .andExpect(MockMvcResultMatchers.status().isOk());;

    }
    
    
}
