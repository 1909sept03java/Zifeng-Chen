package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.*;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	@Modifying
	@Query(value = "insert into COURSE (COURSE_ID, COURSE_NAME) values (:course_id, :course_name)",
	  nativeQuery = true)
	void insertCourse(@Param("course_id") Integer course_id, @Param("course_name") String name);
	

//	@Query(value = "select COURSE.COURSE_NAME from (select COURSE_ID from STUDENT_COURSE where STUDENT_ID=student_id)a left join COURSE ON a.COURSE_ID = COURSE.COURSE_ID ",
//	  nativeQuery = true)
//	List<Course> getMyCourse(@Param("student_id") Integer student_id);
}