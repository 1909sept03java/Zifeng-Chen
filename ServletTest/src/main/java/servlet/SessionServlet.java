package servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.User;



// taking the place of a mapping within the web.xml - annotation-based config
@WebServlet("/session")
public class SessionServlet extends HttpServlet{

	private static final long serialVersionUID = 4485813952366466304L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// grab current session, if it exists, otherwise return null
		HttpSession session = req.getSession(true);
		try {
			// grab session attributes and place them within a user object
			int userId = Integer.parseInt(session.getAttribute("userId").toString());
			String firstname = session.getAttribute("firstname").toString();
			String lastname = session.getAttribute("lastname").toString();
			User u = new User(userId, firstname, lastname);
			// use ObjectMapper (part of the Jackson api) to convert Java object to JSON
			// representation
			resp.getWriter().write((new ObjectMapper()).writeValueAsString(u));
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().write("{\"session\":null}");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
