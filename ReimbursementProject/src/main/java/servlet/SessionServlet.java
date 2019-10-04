package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Reimburse;
import bean.User;
import dao.accountDAOImple;



// taking the place of a mapping within the web.xml - annotation-based config
@WebServlet("/session")
public class SessionServlet extends HttpServlet{

	private static final long serialVersionUID = 4485813952366466304L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// grab current session, if it exists, otherwise return null
		HttpSession session = req.getSession(true);
		
		Enumeration d = req.getParameterNames();
		System.out.println();
		
		while (d.hasMoreElements()) {
	         System.out.println(d.nextElement()); 

	       
			
	      }
		if(true)
		{
			try {
			// grab session attributes and place them within a user object
			int rank = Integer.parseInt(session.getAttribute("rank").toString());
			int id = Integer.parseInt(session.getAttribute("id").toString());
			
			String name = session.getAttribute("name").toString();
			String email = session.getAttribute("email").toString();
			String password = session.getAttribute("password").toString();
			User u = new User(rank,id,name,email,password);
			// use ObjectMapper (part of the Jackson api) to convert Java object to JSON
			// representation
			accountDAOImple a = new accountDAOImple();
			ArrayList<Reimburse> re = a.getReimburse(u);
			ObjectMapper mapper = new ObjectMapper();
			String json = "["+ mapper.writeValueAsString(u);
			if(re!=null) {
				for(Reimburse r:re) {
					json = json + "," +mapper.writeValueAsString(r);
					System.out.println(mapper.writeValueAsString(r) + "\n" + r.toString());
				}
			}
	
			
			if(rank==1) {
				ArrayList<Reimburse> empre = a.getEmployeeRe(u);
				if(empre!=null) {
					for(Reimburse r:empre) {
						json = json + ","+mapper.writeValueAsString(r);
					}
				}
			}
			json = json + "]";
			
			
			resp.getWriter().write(json);
			
			System.out.println(json);
			}
		 catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().write("{\"session\":null}");
		}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
