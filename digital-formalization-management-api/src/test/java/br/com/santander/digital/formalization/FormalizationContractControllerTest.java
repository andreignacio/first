package br.com.santander.digital.formalization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.santander.digital.formalization.adapters.in.controllers.FormalizationContractController;
import br.com.santander.digital.formalization.adapters.in.dto.FormaliationContractRequestDTO;
import br.com.santander.digital.formalization.application.ports.in.ContractServicePort;
import br.com.santander.digital.formalization.util.BeanCreatorForTest;


@SpringBootTest
@AutoConfigureMockMvc
class FormalizationContractControllerTest {
	

	private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;
    
    @InjectMocks
    private FormalizationContractController formalizationContractController;
    
    @Mock
    private ContractServicePort contractServicePort;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(formalizationContractController).build();
    }
    
    @Test
    @DisplayName("Create a Contract with a valid Id Client and Id Product request is provided should return 201 Created")
    public void testCreateContract() throws Exception {
    	
    	FormaliationContractRequestDTO formaliationContractRequestDTO = BeanCreatorForTest.getRequestDTO();
    	
    	
        mockMvc.perform(MockMvcRequestBuilders.post("/digital/formalization/contracts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(formaliationContractRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
               
    }

    
    @Test
    @DisplayName("Create contract when client is invalid should return 400 Bad Request")
    void testCreatetWithInvalidClient() throws Exception {
    	FormaliationContractRequestDTO formaliationContractRequestDTO = BeanCreatorForTest.getRequestDTO();
    	formaliationContractRequestDTO.setClientId(null);
    	
    	
        mockMvc.perform(MockMvcRequestBuilders.post("/digital/formalization/contracts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(formaliationContractRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
    
    @Test
    @DisplayName("Create contract when client is invalid should return 400 Bad Request")
    void testCreatetWithInvalidProduct() throws Exception {
    	FormaliationContractRequestDTO formaliationContractRequestDTO = BeanCreatorForTest.getRequestDTO();
    	formaliationContractRequestDTO.setProductId(null);
    	
    	
        mockMvc.perform(MockMvcRequestBuilders.post("/digital/formalization/contracts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(formaliationContractRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
    
    
    
    
    

}
