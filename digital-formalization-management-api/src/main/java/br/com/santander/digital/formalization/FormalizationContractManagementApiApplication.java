package br.com.santander.digital.formalization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FormalizationContractManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormalizationContractManagementApiApplication.class, args);
	}

}
