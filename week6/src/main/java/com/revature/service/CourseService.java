package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.*;
import com.revature.repository.CourseRepository;
import com.revature.repository.StudentRepository;

@Service
public class CourseService {
	private CourseRepository cr;

	@Autowired
	
	public CourseService(CourseRepository cr) {
		this.cr = cr;
	}
	public List<Course> getAllCourse(){
		return this.cr.findAll();
	}
	@Transactional
	public void addCourse(Course s) {
		
		this.cr.insertCourse(s.getId(),s.getName());

		
	}
	
	public void updateCourse(Course f) {
		System.out.println("here\n"+f.toString());
		this.cr.save(f);
	}

	public void deleteCourse(Course s) {
		this.cr.delete(s);
	}

}