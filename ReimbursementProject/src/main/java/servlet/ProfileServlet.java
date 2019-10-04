package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Reimburse;
import bean.User;
import dao.accountDAOImple;

public class ProfileServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1344565538616036012L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("id") != null) {

				int r = (int) (session.getAttribute("rank")); 

			 resp.sendRedirect("user.html");

				
				
		} else {
			resp.sendRedirect("login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		accountDAOImple e = new accountDAOImple();
		HttpSession session = req.getSession(false);
		System.out.println("here");
		
		
		Enumeration d = req.getParameterNames();
		while (d.hasMoreElements()) {
	         System.out.println(d.nextElement()); 
	     }

		if(req.getParameter("id")!=null) {
			int reid = Integer.parseInt(req.getParameter("id"));
			int rank = Integer.parseInt(session.getAttribute("rank").toString());
			int id = Integer.parseInt(session.getAttribute("id").toString());
			
			String name = session.getAttribute("name").toString();
			String email = session.getAttribute("email").toString();
			String password = session.getAttribute("password").toString();
			User u = new User(rank,id,name,email,password);
			e.acceptReimburse(u, reid);
		}
		
		if(req.getParameter("pass")!=null) {
			System.out.println(req.getParameter("pass"));
			String newpass = req.getParameter("pass");
			
			int rank = Integer.parseInt(session.getAttribute("rank").toString());
			int id = Integer.parseInt(session.getAttribute("id").toString());
			
			String name = session.getAttribute("name").toString();
			String email = session.getAttribute("email").toString();
			String password = session.getAttribute("password").toString();
			User u = new User(rank,id,name,email,password);
			e.modifyUser(u, newpass);
			session.setAttribute("password", newpass);
		}
		if(req.getParameter("money")!=null) {
		System.out.println(req.getParameter("money"));
		
		double money = Double.parseDouble( req.getParameter("money") );
		int rank = Integer.parseInt(session.getAttribute("rank").toString());
		int id = Integer.parseInt(session.getAttribute("id").toString());
		
		String name = session.getAttribute("name").toString();
		String email = session.getAttribute("email").toString();
		String password = session.getAttribute("password").toString();
		User u = new User(rank,id,name,email,password);
		e.makeReimburse(u, money);
		}
		doGet(req, resp);
	}
}
