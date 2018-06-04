package com.example.webdevsummer12018.models.questions;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class MultipleChoice extends Question{
	@Column(name = "CHOICES", nullable = false)
	private String choices;
}
