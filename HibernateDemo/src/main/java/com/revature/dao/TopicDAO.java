package com.revature.dao;

import java.util.List;

import com.revature.beans.Flashcard;
import com.revature.beans.Topic;

public interface TopicDAO {
	
	public Topic getById(int id);
	public List<Flashcard> getFromTopic(Topic topic);
	public List<Topic> getAll();
	public boolean addTopic(Topic topic);
	public boolean updateTopic(Topic topic);
	public boolean deleteTopic(Topic topic);
}
