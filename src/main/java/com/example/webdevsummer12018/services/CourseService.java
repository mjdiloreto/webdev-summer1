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

import com.example.webdevsummer12018.models.Course;
import com.example.webdevsummer12018.models.Module;
import com.example.webdevsummer12018.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll(); 
	}
	
	@GetMapping("/api/course/{courseId}")
	public Course findCourseById(@PathVariable("courseId") int id) {
		Optional<Course> c = courseRepository.findById(id);
		if(c.isPresent()) {
			return c.get();
		}
		return null;
	}

	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}

	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int id) {
		courseRepository.deleteById(id);
	}
	
	// NOTE: Will not delete the modules from database, only add.
	@PutMapping("/api/course")
	public Course updateCourse(@RequestBody Course course) {
		Optional<Course> optCourse = courseRepository.findById(course.getId());
		if(optCourse.isPresent()) {
			Course oldCourse = optCourse.get();
			oldCourse.setTitle(course.getTitle());
			oldCourse.setModified(course.getModified());
			oldCourse.setModules(course.getModules());
			
			return oldCourse;
		}
		return null;
	}
}
