package com.tatva.entities;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor 
@NoArgsConstructor
@Entity(name="TATVA_QUESTIONS")
public class Questions {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@jakarta.persistence.Column(name = "QUESTION_ID", nullable = false)		
	private int questionId;
	
	@Column(name="CREATED_DATE")
	private Timestamp searchedDate;
	
	@Column(name = "QUESTION")	
	private String question;

}
