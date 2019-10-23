package com.revature;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootDemoApplicationTests {


	@Test
	public void contextLoads() {
		
	}
	@Test
	public void findZiFengInAllStudents() {
		Response resp = get("http://localhost:8082/student/all");
		//System.out.println(resp.body("name").print());
		//System.out.println(get("http://localhost:8082/student/all"));
		get("http://localhost:8082/student/all").then().body("name",hasItems("Zi Feng"));
	}
	
	
	
	
}
