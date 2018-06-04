package com.example.webdevsummer12018.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.models.Assignment;
import com.example.webdevsummer12018.models.Lesson;
import com.example.webdevsummer12018.repositories.AssignmentRepository;
import com.example.webdevsummer12018.repositories.LessonRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {

	@Autowired
	AssignmentRepository assignmentRepository;

	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/assignment")
	public Iterable<Assignment> findAllAssignments() {
		return assignmentRepository.findAll(); 
	}
	
	@GetMapping("/api/assignment/{assignmentId}")
	public Assignment findAssignmentById(@PathVariable("assignmentId") int id) {
		Optional<Assignment> a = assignmentRepository.findById(id);
		if(a.isPresent()) {
			return a.get();
		}
		return null;
	}
	
	@PostMapping("/api/lesson/{lessonId}/assignment")
	public Assignment createAssignment(@RequestBody Assignment assignment, 
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> l = lessonRepository.findById(lessonId);
		if(l.isPresent()) {
			Lesson les = l.get();
			assignment.setLesson(les);  // the lesson belongs to this module
			return assignmentRepository.save(assignment);
		}
		return null;
	}
	
	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignment(@PathVariable("assignmentId") int id) {
		assignmentRepository.deleteById(id);
	}
	
	@GetMapping("/api/lesson/{lessonId}/assignment")
	public Iterable<Assignment> findAllAssignmentsForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> l = lessonRepository.findById(lessonId);
		if(l.isPresent()) {
			Lesson les = l.get();
			Iterable<Assignment> assignments = les.getAssignments();
			
			// If there ends up being order for assignments do this.
			//((List<Assignment>) assignments).sort((a, b) -> a.getOrder().compareTo(b.getOrder()));
			
			return assignments;
		}
		return null;
	}
}
