package br.com.santander.digital.formalization.adapters.out.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.santander.digital.formalization.adapters.in.dto.ClientResponseDTO;


@FeignClient(name = "clients", url = "${client.server.url}")
public interface ClientService {

	
	 @GetMapping( "/clients/{id}")
	 ResponseEntity<ClientResponseDTO> getClient(@PathVariable("id") String id);
}
