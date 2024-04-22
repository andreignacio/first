package br.com.santander.digital.formalization.adapters.out.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.santander.digital.formalization.adapters.in.entity.ContractEntity;


public interface ContractRepository extends MongoRepository<ContractEntity, String> {
		

}
