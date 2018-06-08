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
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.models.Lesson;
import com.example.webdevsummer12018.models.questions.Essay;
import com.example.webdevsummer12018.models.questions.FillInTheBlank;
import com.example.webdevsummer12018.models.questions.MultipleChoice;
import com.example.webdevsummer12018.models.questions.Question;
import com.example.webdevsummer12018.models.questions.TrueOrFalse;
import com.example.webdevsummer12018.repositories.ExamRepository;
import com.example.webdevsummer12018.repositories.LessonRepository;
import com.example.webdevsummer12018.repositories.question_repos.EssayRepo;
import com.example.webdevsummer12018.repositories.question_repos.FillInTheBlankRepo;
import com.example.webdevsummer12018.repositories.question_repos.MultipleChoiceRepo;
import com.example.webdevsummer12018.repositories.question_repos.QuestionRepo;
import com.example.webdevsummer12018.repositories.question_repos.TrueOrFalseRepo;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamService {

	@Autowired
	QuestionRepo questionRepo;
	@Autowired
	EssayRepo essayRepo;
	@Autowired
	FillInTheBlankRepo fillInTheBlankRepo;
	@Autowired
	MultipleChoiceRepo multipleChoiceRepo;
	@Autowired
	TrueOrFalseRepo trueOrFalseRepo;
	
	@Autowired 
	ExamRepository examRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/exam")
	public Iterable<Exam> findAllExams() {
		return examRepository.findAll(); 
	}
	
	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int id) {
		Optional<Exam> e = examRepository.findById(id);
		if(e.isPresent()) {
			return e.get();
		}
		return null;
	}
	
	@PostMapping("/api/lesson/{lessonId}/exam")
	public Exam createExam(@RequestBody Exam exam, 
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> l = lessonRepository.findById(lessonId);
		if(l.isPresent()) {
			Lesson les = l.get();
			exam.setLesson(les);  // the lesson belongs to this module
			return examRepository.save(exam);
		}
		return null;
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int id) {
		examRepository.deleteById(id);
	}
	
	@GetMapping("/api/lesson/{lessonId}/exam")
	public Iterable<Exam> findAllExamsForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> l = lessonRepository.findById(lessonId);
		if(l.isPresent()) {
			Lesson les = l.get();
			Iterable<Exam> exams = les.getExams();
			
			// If there ends up being order for assignments do this.
			//((List<Assignment>) assignments).sort((a, b) -> a.getOrder().compareTo(b.getOrder()));
			
			return exams;
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/essay")
	public Essay createEssayForExam(@RequestBody Essay essay,
			@PathVariable("examId") int id) {
		Optional<Exam> e = examRepository.findById(id);
		if(e.isPresent()) {
			Exam exam = e.get();
			essay.setExam(exam);
			return essayRepo.save(essay);
		}
		return null;
	}
	
	@PutMapping("/api/essay/{questionId}")
	public Essay updateEssay(@RequestBody Essay essay, 
			@PathVariable("questionId") int questionId) {
		Optional<Essay> optEssay = essayRepo.findById(questionId);
		if(optEssay.isPresent()) {
			Essay oldEssay = optEssay.get();
			
			if(essay.getTitle() != null) {
				oldEssay.setTitle(essay.getTitle());
			}
			if(essay.getDescription() != null) {
				oldEssay.setDescription(essay.getDescription());
			}
			if(essay.getPoints() != null) {
				oldEssay.setPoints(essay.getPoints());
			}
			
			return essayRepo.save(oldEssay);
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/choice")
	public MultipleChoice createMultipleChoiceForExam(@RequestBody MultipleChoice mc,
			@PathVariable("examId") int id) {
		Optional<Exam> e = examRepository.findById(id);
		if(e.isPresent()) {
			Exam exam = e.get();
			mc.setExam(exam);
			return multipleChoiceRepo.save(mc);
		}
		return null;
	}
	
	@PutMapping("/api/choice/{questionId}")
	public MultipleChoice updateMultipleChoice(@RequestBody MultipleChoice mc, 
			@PathVariable("questionId") int questionId) {
		Optional<MultipleChoice> optMc = multipleChoiceRepo.findById(questionId);
		if(optMc.isPresent()) {
			MultipleChoice oldMc = optMc.get();
			
			if(mc.getTitle() != null) {
				oldMc.setTitle(mc.getTitle());
			}
			if(mc.getDescription() != null) {
				oldMc.setDescription(mc.getDescription());
			}
			if(mc.getPoints() != null) {
				oldMc.setPoints(mc.getPoints());
			}
			if(mc.getChoices() != null) {
				oldMc.setChoices(mc.getChoices());
			}
			
			return multipleChoiceRepo.save(oldMc);
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/blanks")
	public FillInTheBlank createFillInTheBlankForExam(@RequestBody FillInTheBlank fib,
			@PathVariable("examId") int id) {
		Optional<Exam> e = examRepository.findById(id);
		if(e.isPresent()) {
			Exam exam = e.get();
			fib.setExam(exam);
			return fillInTheBlankRepo.save(fib);
		}
		return null;
	}
	
	@PutMapping("/api/blanks/{questionId}")
	public FillInTheBlank updateFillInTheBlank(@RequestBody FillInTheBlank fb, 
			@PathVariable("questionId") int questionId) {
		Optional<FillInTheBlank> oldFb = fillInTheBlankRepo.findById(questionId);
		if(oldFb.isPresent()) {
			FillInTheBlank oldTf = oldFb.get();
			
			if(fb.getTitle() != null) {
				oldTf.setTitle(fb.getTitle());
			}
			if(fb.getDescription() != null) {
				oldTf.setDescription(fb.getDescription());
			}
			if(fb.getPoints() != null) {
				oldTf.setPoints(fb.getPoints());
			}
			if(fb.getVariables() != null) {
				oldTf.setVariables(fb.getVariables());
			}
			
			return fillInTheBlankRepo.save(oldTf);
		}
		return null;
	}
	
	@PutMapping("/api/exam/{examId}")
	public Exam updateExam(@RequestBody Exam exam, 
			@PathVariable("examId") int examId) {
		Optional<Exam> optExam = examRepository.findById(examId);
		if(optExam.isPresent()) {
			Exam oldExam = optExam.get();
			
			if(exam.getTitle() != null) {
				oldExam.setTitle(exam.getTitle());
			}
			if(exam.getDescription() != null) {
				oldExam.setDescription(exam.getDescription());
			}
			if(exam.getPoints() != null) {
				oldExam.setPoints(exam.getPoints());
			}
			
			// Don't update the questions. See if this is needed
			return examRepository.save(oldExam);
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueOrFalse createTrueOrFalseForExam(@RequestBody TrueOrFalse tf,
			@PathVariable("examId") int id) {
		Optional<Exam> e = examRepository.findById(id);
		if(e.isPresent()) {
			Exam exam = e.get();
			tf.setExam(exam);
			return trueOrFalseRepo.save(tf);
		}
		return null;
	}
	
	@PutMapping("/api/truefalse/{questionId}")
	public TrueOrFalse updateTrueOrFalse(@RequestBody TrueOrFalse tf, 
			@PathVariable("questionId") int questionId) {
		Optional<TrueOrFalse> optTf = trueOrFalseRepo.findById(questionId);
		if(optTf.isPresent()) {
			TrueOrFalse oldTf = optTf.get();
			
			if(tf.getTitle() != null) {
				oldTf.setTitle(tf.getTitle());
			}
			if(tf.getDescription() != null) {
				oldTf.setDescription(tf.getDescription());
			}
			if(tf.getPoints() != null) {
				oldTf.setPoints(tf.getPoints());
			}
			if(tf.getIsTrue() != null) {
				oldTf.setIsTrue(tf.getIsTrue());
			}
			
			return trueOrFalseRepo.save(oldTf);
		}
		return null;
	}
}
