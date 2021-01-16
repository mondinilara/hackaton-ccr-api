package br.com.hackaton.ccr.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.hackaton.ccr.annotation.CpfCnpj;
import lombok.Data;

@Data
public class FindUserTeenRequest {

	@Email(message = "E-mail inválido")
	@NotBlank
	private String mail;

	@CpfCnpj
	private String cpf;
}
