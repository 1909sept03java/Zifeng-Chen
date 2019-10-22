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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
public class Student {
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String name) {
		super();
		this.name = name;
	}

	@Id // indicates that this is the primary key!
	// generate values for this PK
	@GeneratedValue(strategy=GenerationType.AUTO, generator="StudentSequence")
	@SequenceGenerator(allocationSize=1, name="StudentSequence", sequenceName="SQ_STUDENT_PK")
	@Column(name="STUDENT_ID")
	private int student_id;
	
	@Column(name="NAME")
	private String name;
	
	@ManyToMany(mappedBy="students", fetch=FetchType.EAGER)
	//private List<Course> cour = new ArrayList<>();
	
	
	
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [id=" + student_id + ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + student_id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (student_id != other.student_id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}