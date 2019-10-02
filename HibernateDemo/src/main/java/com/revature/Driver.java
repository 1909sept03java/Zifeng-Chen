package com.revature;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Flashcard;
import com.revature.beans.Topic;
import com.revature.dao.FlashcardDAO;
import com.revature.dao.FlashcardDAOImpl;
import com.revature.dao.TopicDAOImpl;
import com.revature.util.ConnectionUtil;

public class Driver {
//ee
	public static void main(String[] args) {

		List<Flashcard> flashcardList = new ArrayList<>();
		List<Topic> TopicList = new ArrayList<>();
	
		FlashcardDAOImpl f = new FlashcardDAOImpl();
		TopicDAOImpl t = new TopicDAOImpl();
		
		t.addTopic(new Topic("Topic1"));
		TopicList = t.getAll();
		f.addFlashcard(new Flashcard("Question1","Answer 1",TopicList.get(0)));		
		flashcardList = f.getAll();
		System.out.println(flashcardList.size());
		//System.out.println(TopicList.);
	}

}
