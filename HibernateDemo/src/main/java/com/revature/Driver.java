package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.beans.Flashcard;
import com.revature.dao.FlashcardDAO;
import com.revature.dao.FlashcardDAOImpl;
import com.revature.util.ConnectionUtil;

public class Driver {

	public static void main(String[] args) {
		 System.out.println(ConnectionUtil.getSessionFactory());
		 SessionFactory sf = ConnectionUtil.getSessionFactory();
		 Session s = sf.openSession();
		 System.out.println(s.getStatistics());
		 s.close();
		FlashcardDAO fd = new FlashcardDAOImpl();

		 Flashcard ae = new Flashcard("se","ve");
		 fd.addFlashcard(ae);
		 
		 for (Flashcard f : fd.getAll()) {
			System.out.println(f);
		}
		 ae.setAnswer("deeee");
		 ae.setQuestion("dfgr");
		 fd.updateFlashcard(ae);
		 for (Flashcard f : fd.getAll()) {
			System.out.println(f);
		}
		 System.out.println("\nId2:"+fd.getById(2));
		// System.out.println(fd.getByQuestion("what is your name").toString());
		//System.out.println(fd.getById(2));
		// all of these Flashcards are now detached - they are no longe associated with
		// an open session
	}

}
