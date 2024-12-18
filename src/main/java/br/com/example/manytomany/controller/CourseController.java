package br.com.example.manytomany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.manytomany.entity.Course;
import br.com.example.manytomany.repository.CourseRepository;

@RestController
@RequestMapping("/api/v1")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping("/courses")
	public List<Course> getCourses(){
		return courseRepository.findAll();
	}
	
	@PostMapping("/course")
	public Course saveCourse(@RequestBody Course course){
		return courseRepository.save(course);
	}
	
}
