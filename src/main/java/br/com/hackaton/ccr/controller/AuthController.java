package br.com.hackaton.ccr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hackaton.ccr.dto.UserTeenDto;
import br.com.hackaton.ccr.payload.LoginRequest;
import br.com.hackaton.ccr.repository.UserTeen;
import br.com.hackaton.ccr.service.UserTeenService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	// TODO: AUTENTICAÇÃO E ESQUECI SENHA

	private UserTeenService userTeenService;

	@Autowired
	public AuthController(UserTeenService userTeenService) {
		this.userTeenService = userTeenService;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserTeenDto userTeen) throws Exception {

		log.info("Cadastrando usuário {}", userTeen);

		this.userTeenService.insertUserTeen(userTeen);

		log.info("Response ao cadastrar do usuário {} - {}", userTeen.getCpf(), userTeen.getMail());

		return ResponseEntity.ok().build();
	}

	@PostMapping("/newpassword")
	public ResponseEntity<?> newPassword(@Valid @RequestBody Object passwordRequest) {
		log.info("Gerando nova senha para usuário {}", passwordRequest);

		// ApiResponse response = userService.forgotPassword(passwordRequest);

		log.info("Response gerar nova senha do usuário {}: {}");

		return ResponseEntity.ok().build();
	}
}
