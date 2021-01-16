package br.com.hackaton.ccr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackaton.ccr.dto.UserCompany;
import br.com.hackaton.ccr.exceptions.AppException;
import br.com.hackaton.ccr.repository.UserCompanyRepository;
import br.com.hackaton.ccr.service.UserCompanyService;

@Service
public class UserCompanyServiceImpl implements UserCompanyService {

	private UserCompanyRepository repository;

	@Autowired
	public UserCompanyServiceImpl(UserCompanyRepository repository) {
		this.repository = repository;
	}

	@Override
	public void registerCompany(UserCompany userCompany) throws AppException {
		userCompany.setId(System.currentTimeMillis());
		
		this.repository.save(userCompany);
	}

	@Override
	public List<UserCompany> listUserCompanies() throws AppException {
		return this.repository.findAll();
	}

}
