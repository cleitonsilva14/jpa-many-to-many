package br.com.example.manytomany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.manytomany.entity.Course;
import br.com.example.manytomany.entity.Student;
import br.com.example.manytomany.repository.CourseRepository;
import br.com.example.manytomany.repository.StudentRepository;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	

	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
	@PostMapping("/student")
	public Student addStudent(@RequestBody Student student){
		return studentRepository.save(student);
	}
	
	
	@PutMapping("/student/like/course")
	public Student updateStudent(@RequestParam Integer studentId, @RequestParam Integer courseId) {
		
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("student not found!"));
		
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("course not found!"));
		
		student.getLikedCourses().add(course);
		
		return studentRepository.save(student);
	}
	
	
}
