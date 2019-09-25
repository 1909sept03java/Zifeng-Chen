package servlet;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class HelloWorldServlet extends HttpServlet{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1948572072650728725L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//
		//resp.getWriter().write("Hello World");
		req.getRequestDispatcher("Login.html").forward(req,resp);
		
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		System.out.println(user + "\n" + pass);
	}
}
