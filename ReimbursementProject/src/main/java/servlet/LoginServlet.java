package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Credentials;
import bean.User;
import service.AuthenticationService;

public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1511337217172506014L;
	private AuthenticationService authService = new AuthenticationService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// serve the login.html page as a response
		// RequestDispatcher is used to perform a 'forward' 
		// (pass the request to another resource without the client knowing)
		req.getRequestDispatcher("Login.html").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// check whether a session already exists, and create one if not 
		// overloaded version take a boolean parameter, if false returns null if not session exists
		// matching the incoming request's JSESSIONID token
		HttpSession session = req.getSession();
		// grab credentials from the request - use getParameter for form data
		Credentials creds = new Credentials();
		Enumeration d = req.getParameterNames();
		while (d.hasMoreElements()) {
	         System.out.println(d.nextElement()); 
	      }
		String n = req.getParameter("username");
		if(n==null)
			n="test";
		String p = req.getParameter("password");
		if(p==null)
			p = "pass";
		creds.setUsername(n);
		creds.setPassword(p);
		// pass responsibility for performing auth logic to a service
		User u = authService.authenticateUser(creds);
		if (u != null) {
			// they're real 
			// set user information as session attributes (not request attributes)

			
			session.setAttribute("rank", u.getRank());
			session.setAttribute("id", u.getId());
			session.setAttribute("name", u.getName());
			session.setAttribute("email", u.getEmail());
			session.setAttribute("password", u.getPassword());
			
			session.setAttribute("problem", null);
			// redirect to their profile
			resp.sendRedirect("profile");
		} else {
			// they're not real
			session.setAttribute("problem", "invalid credentials");
			 resp.getWriter().write("invalid credentials");
			// redirect back to login
			resp.sendRedirect("login");
		}
	}
}
