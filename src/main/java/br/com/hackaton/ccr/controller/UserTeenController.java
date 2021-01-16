package br.com.hackaton.ccr.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hackaton.ccr.dto.UserTeen;
import br.com.hackaton.ccr.exceptions.AppException;
import br.com.hackaton.ccr.payload.FindUserTeenRequest;
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
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserTeen request)
			throws AppException, CloneNotSupportedException {

		log.info("Atualizando usuário {}", request);

		this.userTeenService.updateUserTeen(request);

		log.info("Dados do usuário {} atualizados com sucesso!", request.getMail());

		return ResponseEntity.ok().build();
	}

	@GetMapping("/list")
	public ResponseEntity<?> listUsers(@RequestParam(required = false) String city,
			@RequestParam(required = false) List<String> interests,
			@RequestParam(required = false) String educationLevel) throws AppException {

		log.info("Listando usuários com filto(s): {}, {}, {}", city, interests, educationLevel);

		return ResponseEntity.ok().body(this.userTeenService.listUsersTeen(city, interests, educationLevel));
	}

	@PostMapping("/find")
	public ResponseEntity<?> findUser(@Valid @RequestBody FindUserTeenRequest request) throws AppException {

		log.info("Buscando usuário...");

		return ResponseEntity.ok().body(this.userTeenService.findUser(request));
	}
}
