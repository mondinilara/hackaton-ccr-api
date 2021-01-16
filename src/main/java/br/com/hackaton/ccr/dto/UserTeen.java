package br.com.hackaton.ccr.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.annotations.SerializedName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import br.com.hackaton.ccr.annotation.CpfCnpj;
import br.com.hackaton.ccr.utils.Constants;

import lombok.Data;

@Data
@Document(collection = Constants.COLLECTION_USER_TEEN)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserTeen {

	@SerializedName("object_id")
	@Id
	private String objectId;

	@SerializedName("_id")
	private Long id;

	private String name;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth;

	@Indexed(unique = true)
	@CpfCnpj
	private String cpf;

	private List<String> phones;

	@Indexed(unique = true)
	private String mail;

	private String password;

	private Address address;

	private List<String> interests;

	@SerializedName("education_level")
	private String educationLevel;

	private String resume;

	private String objective;

	private List<String> languages;

	@SerializedName("complementary_activities")
	private List<String> complementaryActivities;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@SerializedName("created_on")
	private LocalDateTime createdOn;

}
