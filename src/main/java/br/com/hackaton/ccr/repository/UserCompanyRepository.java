package br.com.hackaton.ccr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.hackaton.ccr.dto.UserCompany;

public interface UserCompanyRepository extends MongoRepository<UserCompany, String> {

}
