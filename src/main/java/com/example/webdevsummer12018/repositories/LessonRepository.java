package com.example.webdevsummer12018.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevsummer12018.models.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
	
}
