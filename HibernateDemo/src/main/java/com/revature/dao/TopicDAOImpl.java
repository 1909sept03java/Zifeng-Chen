package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Flashcard;
import com.revature.beans.Topic;
import com.revature.util.ConnectionUtil;

public class TopicDAOImpl implements TopicDAO{
	private SessionFactory sf = ConnectionUtil.getSessionFactory();
	
	@Override
	public Topic getById(int id) {
		Topic f = null;
		
		try (Session s = sf.openSession()) {
			f = s.get(Topic.class, id);
			System.out.println(s.getStatistics());
		}
		return f;
	}
	@Override
	public List<Flashcard> getFromTopic(Topic topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getAll() {
		List<Topic> topicList = new ArrayList<>();
		// use a Query
		try (Session s = sf.openSession()) {
			topicList = s.createQuery("from Topic").getResultList();
			System.out.println(s.getStatistics());
		}
		return topicList;
	}

	@Override
	public boolean addTopic(Topic topic) {
		boolean added = false;
		try (Session s = sf.openSession()) {
			Transaction tx = s.beginTransaction();
			s.persist(topic);
			tx.commit();
			added = true;
		}
		return added;
	}
	@Override
	public boolean updateTopic(Topic topic) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteTopic(Topic topic) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	
}
