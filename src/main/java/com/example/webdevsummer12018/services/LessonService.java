package com.example.webdevsummer12018.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.models.Lesson;
import com.example.webdevsummer12018.models.Module;
import com.example.webdevsummer12018.repositories.LessonRepository;
import com.example.webdevsummer12018.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ModuleRepository moduleRepository;
	
	@GetMapping("/api/lesson")
	public Iterable<Lesson> findAllLessons() {
		return lessonRepository.findAll(); 
	}
	
	@GetMapping("/api/lesson/{lessonId}")
	public Lesson findLessonById(@PathVariable("lessonId") int id) {
		Optional<Lesson> l = lessonRepository.findById(id);
		if(l.isPresent()) {
			return l.get();
		}
		return null;
	}

	// NOTE: Will associate the lesson with the module, regardless of whether the courseId
	// is the id for the course the module belongs to.
	// NOTE: Will return null if the module does not exist though.
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public Lesson createLesson(@RequestBody Lesson lesson, 
			@PathVariable("courseId") int courseId, 
			@PathVariable("moduleId") int moduleId) {
		Optional<Module> m = moduleRepository.findById(moduleId);
		if(m.isPresent()) {
			Module mod = m.get();
			lesson.setModule(mod);  // the lesson belongs to this module
			return lessonRepository.save(lesson);
		}
		return null;
	}

	@DeleteMapping("/api/lesson/{lessonId}")
	public void deleteLesson(@PathVariable("lessonId") int id) {
		lessonRepository.deleteById(id);
	}
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public Iterable<Lesson> findAllLessonsForModule(@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId) {
		Optional<Module> m = moduleRepository.findById(moduleId);
		if(m.isPresent()) {
			Module mod = m.get();
			return mod.getLessons();
		}
		return null;
	}
	
	@PutMapping("/api/lesson/{lessonId}")
	public Lesson updateLesson(@RequestBody Lesson lesson, @PathVariable("lessonId") int lessonId) {
		Optional<Lesson> optLesson = lessonRepository.findById(lessonId);
		if(optLesson.isPresent()) {
			Lesson oldLesson = optLesson.get();
			
			if(lesson.getModule() != null) {
				oldLesson.setModule(lesson.getModule());
			}
			if(lesson.getTitle() != null) {
				oldLesson.setTitle(lesson.getTitle());
			}
			
			return oldLesson;
		}
		return null;
	}
}
