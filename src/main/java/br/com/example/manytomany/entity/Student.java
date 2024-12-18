package br.com.example.manytomany.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor 
@NoArgsConstructor
@Getter @Setter
@Table(name = "tb_student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;
	private String name;
	
	@JsonIgnoreProperties(value = "likes")
	@ManyToMany
	@JoinTable(name = "tb_student_course",
		joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "studentId")},
		inverseJoinColumns = {
				@JoinColumn(name = "course_id", referencedColumnName = "courseId")
		}
	)
	private Set<Course> likedCourses = new HashSet<>();
	
}







