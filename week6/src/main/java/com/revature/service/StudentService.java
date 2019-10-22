package com.revature.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Student;
import com.revature.repository.*;
@Service
public class StudentService {
	private StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	public List<Student> getAllStudent(){
		return this.studentRepository.findAll();
	}
	//public Student getStudentById(int student_id) {
	//	return this.studentRepository.findById(student_id).orElse(null);
	//}
@Transactional
	public void addStudent(Student s) {
		
		this.studentRepository.insertStudent(s.getId(), s.getName());

		
	}
	public void updateStudent(Student f) {
		System.out.println("here\n"+f.toString());
		this.studentRepository.save(f);
	}
	public void deleteStudent(Student s) {
		this.studentRepository.delete(s);
	}
}
