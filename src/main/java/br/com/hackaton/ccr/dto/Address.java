package br.com.hackaton.ccr.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Address {

	@NotBlank
	private String street;

	@NotBlank
	private String city;

	@NotBlank
	private String state;

	@NotBlank
	private Integer number;

}
