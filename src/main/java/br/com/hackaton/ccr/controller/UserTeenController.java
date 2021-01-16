package br.com.hackaton.ccr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hackaton.ccr.dto.UserTeenDto;
import br.com.hackaton.ccr.service.UserTeenService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user/teen")
public class UserTeenController {

	private UserTeenService userTeenService;

	@Autowired
	public UserTeenController(UserTeenService userTeenService) {
		this.userTeenService = userTeenService;
	}

	@PostMapping("/update")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserTeenDto userTeen) throws Exception {

		log.info("Atualizando usuário {}", userTeen);

		this.userTeenService.updateUserTeen(userTeen);

		log.info("Dados do usuário {} - {} atualizados com sucesso!", userTeen.getCpf(), userTeen.getMail());

		return ResponseEntity.ok().build();
	}
}
