# Spring JPA @ManyToMany

### application.properties - Mysql
```markdown
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/many_to_many_db
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql: true
server.port=8888
```

__Student.java__
```java
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
```
__Course.java__
```java
@Entity
@AllArgsConstructor 
@NoArgsConstructor
@Getter @Setter
@Table(name = "tb_course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseId;
	private String name;
	
	@JsonIgnoreProperties(value = "likedCourses")
	@ManyToMany(mappedBy = "likedCourses")
	private Set<Student> likes;
}
```

POST student
```json
{
    "name": "Student 01"
}
```

POST course

```json
{
    "name": "MySQL 8 Completo"
}
```

PUT 

http://localhost:8888/api/v1/student/like/course?studentId=1&courseId=4



__StudentController.java__
```java
...
@PutMapping("/student/like/course")
	public Student updateStudent(@RequestParam Integer studentId, @RequestParam Integer courseId) {
		
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("student not found!"));
		
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("course not found!"));
	
		student.getLikedCourses().add(course);
		
		return studentRepository.save(student);
	}
...
```
