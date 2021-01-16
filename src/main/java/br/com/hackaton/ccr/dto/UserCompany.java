package br.com.hackaton.ccr.dto;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.annotations.SerializedName;

import br.com.hackaton.ccr.annotation.CpfCnpj;
import br.com.hackaton.ccr.utils.Constants;
import lombok.Data;

@Data
@Document(collection = Constants.COLLECTION_USER_COMPANY)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserCompany {

	@SerializedName("object_id")
	@Id
	private String objectId;

	@SerializedName("_id")
	private Long id;
	
	private String name;
	
	@SerializedName("image_base64")
	private String imageBase64;
	
	@CpfCnpj
	private String cnpj;
	
	private List<Job> jobs;
}
