package com.tatva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tatva.entities.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer>{
	

	
}
