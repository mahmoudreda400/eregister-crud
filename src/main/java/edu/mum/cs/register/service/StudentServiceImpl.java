package edu.mum.cs.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.mum.cs.register.models.Student;
import edu.mum.cs.register.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studenRepo;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepo) {
		this.studenRepo = studentRepo;
	}

	@Override
	public Page<Student> getStudentPage(int pageNo) {
		return studenRepo.findAll(PageRequest.of(pageNo, 3, Sort.by("firstName")));
	}

	@Override
	public Student saveStudent(Student student) {
		return studenRepo.save(student);
	}

	@Override
	public Student getStudentById(long id) {
		return studenRepo.findById(id).get();
	}

	@Override
	public void deleteStudentById(long id) {
		studenRepo.deleteById(id);
	}

	@Override
	public Page<Student> search(int pageNo,String keyword) {
		return studenRepo.search(PageRequest.of(pageNo, 10), keyword);
	}

}
