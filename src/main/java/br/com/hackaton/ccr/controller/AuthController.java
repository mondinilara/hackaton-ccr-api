package br.com.hackaton.ccr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hackaton.ccr.dto.UserTeen;
import br.com.hackaton.ccr.exceptions.AppException;
import br.com.hackaton.ccr.payload.JwtAuthenticationResponse;
import br.com.hackaton.ccr.payload.LoginRequest;
import br.com.hackaton.ccr.service.UserTeenService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private UserTeenService userTeenService;

	@Autowired
	public AuthController(UserTeenService userTeenService) {
		this.userTeenService = userTeenService;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws AppException {
		log.info("Autenticando usuário {}", loginRequest.getMail());

		JwtAuthenticationResponse response = userTeenService.loginUserTeen(loginRequest);

		log.info("Usuário {} autenticado", loginRequest.getMail());

		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserTeen userTeen) throws AppException {

		log.info("Cadastrando usuário {}", userTeen.getMail());

		this.userTeenService.registerUserTeen(userTeen);

		log.info("Usuário {} cadastrado com sucesso!", userTeen.getMail());

		return ResponseEntity.ok().build();
	}

	// TODO
	@PostMapping("/newpassword")
	public ResponseEntity<?> newPassword(@Valid @RequestBody Object passwordRequest) {
		log.info("Gerando nova senha para usuário {}", passwordRequest);

		// ApiResponse response = userTeenService.forgotPassword(passwordRequest);

		log.info("Response gerar nova senha do usuário {}: {}");

		return ResponseEntity.ok().build();
	}
}
