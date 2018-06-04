package com.example.webdevsummer12018.models.questions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class TrueOrFalse extends Question {
	@Column(name = "IS_TRUE", nullable = false)
	private Boolean isTrue;
	public Boolean getIsTrue() {
		return isTrue;
	}
	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}
}
