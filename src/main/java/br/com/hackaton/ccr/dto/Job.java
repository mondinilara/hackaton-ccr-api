package br.com.hackaton.ccr.dto;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Job {
	
	@SerializedName("object_id")
	@Id
	private String objectId;
	
	private String name;
	
	private String resume;
	
	private List<String> prerequisites;
	
	private String salary;
	
	private List<String> interests;
	
	private Address address;

}
