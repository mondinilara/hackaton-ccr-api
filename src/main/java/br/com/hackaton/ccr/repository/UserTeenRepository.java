package br.com.hackaton.ccr.repository;

import br.com.hackaton.ccr.dto.UserTeen;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserTeenRepository extends MongoRepository<UserTeen, String> {

	UserTeen findFirstByCpf(String cpf);

	UserTeen findFirstByMail(String mail);

	UserTeen findFirstById(Long id);
	
	UserTeen findFirstByCpfOrMail(String cpf, String mail);
	
	UserTeen findFirstByCpfAndMail(String cpf, String mail);

}
