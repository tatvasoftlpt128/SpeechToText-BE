package com.tatva.service;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.tatva.entities.Questions;
import com.tatva.repositories.QuestionsRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	
	private final QuestionsRepository questionsRepository;
	/*
	 * Store searched questions in database
	 */
	@Transactional
	public void storeQuestion(String que) {
		Questions question=new Questions();
		question.setQuestion(que);
		question.setSearchedDate(new Timestamp(System.currentTimeMillis()));
		questionsRepository.save(question);
		
	}

}
