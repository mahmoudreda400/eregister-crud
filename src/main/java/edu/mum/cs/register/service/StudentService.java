package edu.mum.cs.register.service;


import org.springframework.data.domain.Page;

import edu.mum.cs.register.models.Student;

public interface StudentService {

	Page<Student>getStudentPage(int pageNo);
	
	Student saveStudent(Student student);
	
	Student getStudentById(long id);
	
	void deleteStudentById(long id);
	
	Page<Student> search(int pageNo, String keyword);
}
