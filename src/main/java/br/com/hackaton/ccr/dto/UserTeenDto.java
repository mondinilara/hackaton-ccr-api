package br.com.hackaton.ccr.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.annotations.SerializedName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import br.com.hackaton.ccr.utils.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
@Document(collection = Constants.COLLECTION_USER_TEEN)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserTeenDto {

	@SerializedName("_id")
	private Long id;

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
	@SerializedName("education_level")
	private String educationLevel;

	@NotBlank
	private String resume;

	@NotBlank
	private String objective;

	@NotBlank
	private List<String> languages;

	@NotBlank
	@SerializedName("complementary_activities")
	private List<String> complementaryActivities;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@SerializedName("created_on")
	private LocalDateTime createdOn;

}
