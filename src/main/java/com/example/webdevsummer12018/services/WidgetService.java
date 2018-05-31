package com.example.webdevsummer12018.services;

import java.util.List;
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
import com.example.webdevsummer12018.models.Widget;
import com.example.webdevsummer12018.repositories.LessonRepository;
import com.example.webdevsummer12018.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@PostMapping("/api/widget/save")
	public void saveAllWidgets(@RequestBody Iterable<Widget> widgets) {
		widgetRepository.saveAll(widgets);
	}
	
	@GetMapping("/api/widget")
	public Iterable<Widget> findAllWidgets() {
		return widgetRepository.findAll(); 
	}
	
	@GetMapping("/api/widget/{widgetId}")
	public Widget findLessonById(@PathVariable("widgetId") int id) {
		Optional<Widget> w = widgetRepository.findById(id);
		if(w.isPresent()) {
			return w.get();
		}
		return null;
	}
	
	@PostMapping("/api/lesson/{lessonId}/widget")
	public Widget createWidget(@RequestBody Widget widget, 
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> l = lessonRepository.findById(lessonId);
		if(l.isPresent()) {
			Lesson les = l.get();
			widget.setLesson(les);  // the lesson belongs to this module
			return widgetRepository.save(widget);
		}
		return null;
	}
	
	@PostMapping("/api/lesson/{lessonId}/widgets")
	public Iterable<Widget> createAllWidgetsForLesson(@RequestBody Iterable<Widget> widgets,
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> l = lessonRepository.findById(lessonId);
		if(l.isPresent()) {
			Lesson les = l.get();
			for(Widget w: widgets) {
				w.setLesson(les);
			}
			return widgetRepository.saveAll(widgets);
		}
		return null;
	}

	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int id) {
		widgetRepository.deleteById(id);
	}
	
	@GetMapping("/api/lesson/{lessonId}/widget")
	public Iterable<Widget> findAllWidgetsForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> l = lessonRepository.findById(lessonId);
		if(l.isPresent()) {
			Lesson les = l.get();
			Iterable<Widget> widgets = les.getWidgets();
			
			((List<Widget>) widgets).sort((a, b) -> a.getOrder().compareTo(b.getOrder()));
			
			return widgets;
		}
		return null;
	}
	
	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(@RequestBody Widget widget, @PathVariable("widgetId") int widgetId) {
		Optional<Widget> optWidget = widgetRepository.findById(widgetId);
		if(optWidget.isPresent()) {
			Widget oldWidget = optWidget.get();
			
			oldWidget.setAll(widget);
			
			return oldWidget;
		}
		return null;
	}
}
