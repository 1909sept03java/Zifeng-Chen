package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="TOPIC")
public class Topic {
	
	
	@Override
	public String toString() {
		if( id == 0 || name == null)
			return "No Topic";
		else
			return "Topic [id=" + id + ", name=" + name + "]";
	}
	@Id // indicates that this is the primary key! ("persistent identity" of a Flashcard)
	// generate values for this PK
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="TopicSequence")
	@SequenceGenerator(allocationSize=1, name="TopicSequence", sequenceName="SQ_TOPIC_PK")
	@Column(name="TOPIC_ID")
	private int id=0;
	// don't strictly need these unless you want to customize your column definitions
	@Column(name="TOPIC_NAME")
	private String name;

	@OneToMany(mappedBy = "topic",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Flashcard> flashcards;// = new HashSet<>();

	public void addflash(Flashcard flashcard) {
		flashcards.add(flashcard);
	}

	public Topic(String name) {
	super();
	this.name = name;
	//this.flashcards = flashcards;
	}


	public Topic() {
		super();
		// TODO Auto-generated constructor stub
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

	
	
	
	
}
