package br.com.hackaton.ccr.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {

	@NotBlank
	@Email(message = "E-mail inválido")
	private String email;

	@NotBlank
	private String password;
}
