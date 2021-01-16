package br.com.hackaton.ccr.service;

import java.util.List;

import br.com.hackaton.ccr.dto.UserCompany;
import br.com.hackaton.ccr.exceptions.AppException;

public interface UserCompanyService {

	void registerCompany(UserCompany userCompany) throws AppException;

	List<UserCompany> listUserCompanies() throws AppException;
}
