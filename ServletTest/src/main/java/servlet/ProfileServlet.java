package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1344565538616036012L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("userId") != null) {
			
			resp.sendRedirect("newPage.html");
			
				resp.getWriter().write("welcome to your profile, " + session.getAttribute("firstname") + " "
						+ session.getAttribute("lastname"));
				
				
				
				
		} else {
			resp.sendRedirect("login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
