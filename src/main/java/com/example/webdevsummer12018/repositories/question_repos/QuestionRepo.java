package com.example.webdevsummer12018.repositories.question_repos;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer12018.models.questions.Question;

public interface QuestionRepo extends CrudRepository<Question, Integer>{

}
