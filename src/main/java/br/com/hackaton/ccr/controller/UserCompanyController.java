package br.com.hackaton.ccr.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hackaton.ccr.dto.UserCompany;
import br.com.hackaton.ccr.exceptions.AppException;
import br.com.hackaton.ccr.service.UserCompanyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user/company")
public class UserCompanyController {

	private UserCompanyService userCompanyService;

	public UserCompanyController(UserCompanyService userCompanyService) {
		this.userCompanyService = userCompanyService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserCompany userCompany) throws AppException {

		log.info("Cadastrando empresa {}", userCompany.getName());

		this.userCompanyService.registerCompany(userCompany);

		log.info("Empresa {} cadastrada com sucesso!", userCompany.getName());

		return ResponseEntity.ok().build();
	}

	@GetMapping("/list")
	public ResponseEntity<?> listUsers() throws AppException {

		log.info("Listando empresas...");

		return ResponseEntity.ok().body(this.userCompanyService.listUserCompanies());
	}
}
