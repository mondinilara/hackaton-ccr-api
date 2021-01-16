package br.com.hackaton.ccr.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EducationLevel {

	// @formatter:off
	ELEMENTARY_SCHOOL_INCOMPLETE("Ensino Fundamental Incompleto"),
	ELEMENTARY_SCHOOL_COMPLETE("Ensino Fundamental Completo"), 
	HIGH_SCHOOL_INCOMPLETE("Ensino Médio Incompleto"),
	HIGH_SCHOOL_COMPLETE("Ensino Médio Completo"), 
	UNIVERSITY_INCOMPLETE("Ensino Superior Incompleto"),
	UNIVERSITY_COMPLETE("Ensino Superior Completo"), 
	POS_INCOMPLETE("Pos Graduação Incompleto"),
	POS_COMPLETE("Pós Graduação Completo");
	// @formatter:on
	
	private String level;

	EducationLevel(String level) {
		this.level = level;
	}

	@JsonValue
	public String getLevel() {
		return this.level;
	}

}
