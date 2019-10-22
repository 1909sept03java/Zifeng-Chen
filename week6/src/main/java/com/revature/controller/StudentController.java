package com.revature.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Student;
import com.revature.service.*;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping(value = "/student")
public class StudentController {
	private StudentService studentService;

	@Autowired // setter injection
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getAll() {
		return new ResponseEntity<>(this.studentService.getAllStudent(), HttpStatus.OK);
	}
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public ResponseEntity<String> addStudent(@Valid @RequestBody Student student) {
		ResponseEntity<String> resp = null;
		try {
			Student t = student;
			System.out.println(t);
			this.studentService.addStudent(t);
			resp = new ResponseEntity<>("student CREATED SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<>("FAILED TO CREATE student", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	@RequestMapping(value = "/update",method = RequestMethod.PUT)
	public ResponseEntity<String> updateStudent(@Valid @RequestBody Student student) {
		ResponseEntity<String> resp = null;
		try {
			this.studentService.updateStudent(student);
			resp = new ResponseEntity<>("student UPDATED SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<>("FAILED TO UPDATED student", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	@RequestMapping(value = "/delete",method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteStudent(@Valid @RequestBody Student student) {
		ResponseEntity<String> resp = null;
		try {
			this.studentService.deleteStudent(student);
			resp = new ResponseEntity<>("student DELETE SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<>("FAILED TO DELETE student", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
}
