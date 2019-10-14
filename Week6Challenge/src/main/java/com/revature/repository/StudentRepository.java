package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.revature.model.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	//public List<Student> getBystudent_Id(int student_id);
	@Modifying
	@Query(value = "insert into Student (student_id, name) values (:student_id, :name)",
	  nativeQuery = true)
	void insertStudent(@Param("student_id") Integer student_id, @Param("name") String name);
}
