package br.com.hackaton.ccr.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@NotBlank
	@Email(message = "E-mail inválido")
	private String mail;

	@NotBlank
	private String password;
}
