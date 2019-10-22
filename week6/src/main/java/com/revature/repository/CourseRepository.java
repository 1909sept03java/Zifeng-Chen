package com.revature.repository;

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
}