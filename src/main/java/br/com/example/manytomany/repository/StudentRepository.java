package br.com.example.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.example.manytomany.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	
}
