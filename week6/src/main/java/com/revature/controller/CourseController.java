package com.revature.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Course;
import com.revature.model.Student;
import com.revature.service.CourseService;
import com.revature.service.StudentService;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping(value = "/course")
public class CourseController {
	private CourseService courseService;

	@Autowired // setter injection
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Course>> getAll() {
		return new ResponseEntity<>(this.courseService.getAllCourse(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Course>> getMyCourse(@PathVariable int id) {
		return new ResponseEntity<>(this.courseService.getMyCourse(id),HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public ResponseEntity<String> addCourse(@Valid @RequestBody Course c) {
		ResponseEntity<String> resp = null;
		try {
		
			this.courseService.addCourse(c);
			resp = new ResponseEntity<>("Course CREATED SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<>("FAILED TO CREATE Course", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
	
	@RequestMapping(value = "/update",method = RequestMethod.PUT)
	public ResponseEntity<String> updateCourse(@Valid @RequestBody Course c) {
		ResponseEntity<String> resp = null;
		try {
			this.courseService.updateCourse(c);
			resp = new ResponseEntity<>("Course update SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<>("FAILED TO update Course", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
	@RequestMapping(value = "/enroll",method= RequestMethod.PUT)
	public ResponseEntity<String>updateCourseWithStudent(@Valid @RequestBody Course c,Student s){
		List<Student> a = c.getStudents();
		a.add(s);
		c.setStudents(a);
		ResponseEntity<String> resp = null;
		try {
			this.courseService.updateCourse(c);
			resp = new ResponseEntity<>("Course update SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<>("FAILED TO update Course", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCourse(@Valid @RequestBody Course c) {
		ResponseEntity<String> resp = null;
		try {
			this.courseService.deleteCourse(c);
			resp = new ResponseEntity<>("Course Delete", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<>("FAILED TO Delete", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
}