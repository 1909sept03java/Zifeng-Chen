package com.revature.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.POST)
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
	@RequestMapping(method = RequestMethod.PUT)
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
	@RequestMapping(method = RequestMethod.DELETE)
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