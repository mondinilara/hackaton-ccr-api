package br.com.hackaton.ccr.dto;

import lombok.Data;

@Data
public class Address {

	private String street;

	private String city;

	private String state;

	private Integer number;

	private Double latitude;

	private Double longitude;

}
