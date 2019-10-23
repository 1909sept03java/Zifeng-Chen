package com.revature.RestAssuredTester;


import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.hasItems;

import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTest {	
	@Test
	@Order(1)
	public void findZiFengInAllStudents() {
		Response resp = get("http://localhost:8082/student/all");
		get("http://localhost:8082/student/all").then().body("name",hasItems("Zi Feng"));
	}
	@Test
	public void testAllCourses() {
		get("http://localhost:8082/course/all").then().body("name", hasItems("Programming","Art"));
	}

	@Test
	@Order(2)
	public void testPutUpdateStudent() {
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		try {
			requestParams.put("student_id", 2);
			requestParams.put("name", "Khanh");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		request.header("Content-Type", "application/json");
		request.body(requestParams.toString());
		Response response = request.put("http://localhost:8082/student/update");
		Assert.assertEquals(response.body().print(),"student UPDATED SUCCESSFULLY");
	}
	@Test 
	@Order(4)
	public void testDeleteRemoveStudent() {
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		try {
			requestParams.put("student_id", 6);
			requestParams.put("name", "Rex");
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		request.header("Content-Type", "application/json");
		request.body(requestParams.toString());
		Response response = request.delete("http://localhost:8082/student/delete");
		Assert.assertEquals(response.body().print(),"student DELETE SUCCESSFULLY");
	}
	@Test
	@Order(3)
	public void testPostAddStudent() {
		
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		try {
			requestParams.put("student_id", 6);
			requestParams.put("name", "Rex");
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		
		request.header("Content-Type", "application/json");
		request.body(requestParams.toString());
		Response response = request.post("http://localhost:8082/student/add");
		Assert.assertEquals(response.body().print(),"student CREATED SUCCESSFULLY");
		
	}
}
