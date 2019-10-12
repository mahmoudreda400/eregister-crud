package edu.mum.cs.register.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.mum.cs.register.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	@Query("select s from Student s where firstName like %:keyword% or middleName like %:keyword% "
			+ "or lastName like %:keyword% or studentNumber like %:keyword%")
	Page<Student> search(Pageable pageable,@Param("keyword") String keyword);
}
