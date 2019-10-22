
package com.revature.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
@Entity
@Table(name="COURSE")
public class Course {
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Course(String name) {
		super();
		this.name = name;
	}
	@Id // indicates that this is the primary key!
	// generate values for this PK
	@GeneratedValue(strategy=GenerationType.AUTO, generator="CourseSequence")
	@SequenceGenerator(allocationSize=1, name="CourseSequence", sequenceName="SQ_COURSE_PK")
	@Column(name="COURSE_ID")
	private int id;
	@Column(name="COURSE_NAME")
	private String name;

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="STUDENT_COURSE",
			joinColumns = {@JoinColumn(name="COURSE_ID")},
			inverseJoinColumns = {@JoinColumn(name="STUDENT_ID")})
	private List<Student> students = new ArrayList<>();


	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Course(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	
	

}