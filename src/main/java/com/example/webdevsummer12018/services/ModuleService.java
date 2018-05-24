package com.example.webdevsummer12018.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.webdevsummer12018.models.Course;
import com.example.webdevsummer12018.models.Lesson;
import com.example.webdevsummer12018.models.Module;
import com.example.webdevsummer12018.repositories.CourseRepository;
import com.example.webdevsummer12018.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	ModuleRepository moduleRepository;	
	
	@GetMapping("/api/module")
	public List<Module> findAllModules() {
		return (List<Module>) moduleRepository.findAll();
	}
	
	@GetMapping("/api/lesson/{moduleId}")
	public Module findModuleById(@PathVariable("moduleId") int id) {
		Optional<Module> m = moduleRepository.findById(id);
		if(m.isPresent()) {
			return m.get();
		}
		return null;
	}
	
	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(
			@PathVariable("courseId") int courseId,
			@RequestBody Module newModule) {
		Optional<Course> data = courseRepository.findById(courseId);
		
		if(data.isPresent()) {
			Course course = data.get();
			newModule.setCourse(course);
			return moduleRepository.save(newModule);
		}
		return null;		
	}
	
	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModulesForCourse(
			@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			return course.getModules();
		}
		return null;		
	}
	
	@DeleteMapping("/api/module/{moduleId}")
	public void deleteModule(@PathVariable("moduleId") int moduleId) {
		moduleRepository.deleteById(moduleId);
	}
	
	@PutMapping("/api/module/{moduleId}")
	public Module updateModule(@RequestBody Module module, @PathVariable("moduleId") int moduleId) {
		Optional<Module> optModule = moduleRepository.findById(moduleId);
		if(optModule.isPresent()) {
			Module oldModule = optModule.get();
			
			if(module.getCourse() != null) {
				oldModule.setCourse(module.getCourse());
			}
			if(module.getTitle() != null) {
				oldModule.setTitle(module.getTitle());
			}
			if(module.getLessons() != null) {
				oldModule.setLessons(module.getLessons());
			}
			
			return oldModule;
		}
		return null;
	}
}
