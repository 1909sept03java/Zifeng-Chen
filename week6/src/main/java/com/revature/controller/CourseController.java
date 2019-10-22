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
	private StudentService studentService;
	@Autowired // setter injection
	public void setCourseService(CourseService courseService, StudentService studentService) {
		this.courseService = courseService;
		this.studentService = studentService;
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
	
	@RequestMapping(value = "/enroll",method= RequestMethod.POST)
	public ResponseEntity<String>updateCourseWithStudent(@Valid @RequestBody  String s){
		System.out.println(s);
		boolean flag = false;
		byte[] jsonData = s.getBytes(Charset.forName("UTF-8"));
		int student_id = 0;
		int course_id = 0;
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseEntity<String> resp = null;
		//read JSON like DOM Parser
		JsonNode rootNode;
		try {
			rootNode = objectMapper.readTree(jsonData);
			JsonNode idNode = rootNode.path("student_id");
			System.out.println("student_id = "+idNode.asText());
			student_id = idNode.asInt();
			
			idNode= rootNode.path("course_id");
			System.out.println("course_id = "+idNode.asText());
			course_id = idNode.asInt();	
			
			Course tempc = this.courseService.getCourseById(course_id);
			Student temps = this.studentService.getStudentById(student_id);
			List<Student> temparr = tempc.getStudents();
			temparr.add(temps);
			tempc.setStudents(temparr);
			this.courseService.updateCourse(tempc);
			
		} catch (Exception e1) {
			e1.printStackTrace();
				return new ResponseEntity<>("Something in the back end has an issue",HttpStatus.BAD_REQUEST);
		}
		return  new ResponseEntity<>("Success",HttpStatus.OK);
	}
	@RequestMapping(value = "/drop",method= RequestMethod.POST)
	public ResponseEntity<String>dropCourseWithStudent(@Valid @RequestBody String s){
		System.out.println(s);
		boolean flag = false;
		byte[] jsonData = s.getBytes(Charset.forName("UTF-8"));
		int student_id = 0;
		int course_id = 0;
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseEntity<String> resp = null;
		//read JSON like DOM Parser
		JsonNode rootNode;
		try {
			rootNode = objectMapper.readTree(jsonData);
			JsonNode idNode = rootNode.path("student_id");
			System.out.println("student_id = "+idNode.asText());
			student_id = idNode.asInt();
			
			idNode= rootNode.path("course_id");
			System.out.println("course_id = "+idNode.asText());
			course_id = idNode.asInt();	
			
			Course tempc = this.courseService.getCourseById(course_id);
			Student temps = this.studentService.getStudentById(student_id);
			List<Student> temparr = tempc.getStudents();
			temparr.remove(temps);
			tempc.setStudents(temparr);
			this.courseService.updateCourse(tempc);
			
		} catch (Exception e1) {
			e1.printStackTrace();
				return new ResponseEntity<>("Something in the back end has an issue",HttpStatus.BAD_REQUEST);
		}
		return  new ResponseEntity<>("Success",HttpStatus.OK);
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