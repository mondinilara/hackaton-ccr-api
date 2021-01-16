package br.com.hackaton.ccr.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import br.com.hackaton.ccr.enums.EducationLevel;

import java.time.LocalDate;

import lombok.Data;

@Document
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserTeenDto {

	private ObjectId id;

	@NotBlank
	private String name;

	@NotBlank
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth;

	@Indexed(unique = true)
	@NotBlank
	private String cpf;

	@NotBlank
	private List<String> phones;

	@Indexed(unique = true)
	@NotBlank
	private String mail;

	@NotBlank
	private String password;

	@NotBlank
	private Address address;

	@NotBlank
	private List<String> interests;

	@NotBlank
	private EducationLevel educationLevel;

	@NotBlank
	private String resume;

	@NotBlank
	private String objective;

	@NotBlank
	private List<String> languages;

	@NotBlank
	private List<String> complementaryActivities;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdOn;

}
