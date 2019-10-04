package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import bean.Reimburse;
import bean.User;
import dao.accountDAOImple;

public class ProcessServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3750837390957586355L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// grab current session, if it exists, otherwise return null
		HttpSession session = req.getSession(true);
		String payloadRequest = getBody(req);
//System.out.println(payloadRequest);
		String[] splitterString = payloadRequest.split("\"");
		for (String s : splitterString) {
			System.out.println(s);
		}
		String act = splitterString[3];
		
		
		
		if(act.equals("useracc")) {
			
			System.out.println("\n\nHERE\n\n");
			System.out.println(splitterString[7]);
			int temp=0;
			try {
				temp = Integer.parseInt(splitterString[7]);
				System.out.println(temp);
			}catch(NumberFormatException e) {
				temp=0;
				System.out.println("errpr");
			}
			
			
		
			try {
				accountDAOImple a = new accountDAOImple();
				User us = new User(1,temp,a.getNameWithId(temp),"ddd","dddd");
				ArrayList<Reimburse> u = a.getReimburse(us);
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(u);
				System.out.println(u);
				resp.getWriter().write(json);
			} catch (Exception e) {
				e.printStackTrace();
				resp.getWriter().write("{\"session\":null}");
			}
			
			
			
		}
		
		
		if(act.equals("empthenre")) {

			Pattern p = Pattern.compile("[a-z]+|\\d+");
			Matcher m = p.matcher(splitterString[6]);
			ArrayList<String> allMatches = new ArrayList<>();
			while (m.find()) {
			    allMatches.add(m.group());
			}
			System.out.println(allMatches.get(0));
			try {
			accountDAOImple a = new accountDAOImple();
			ArrayList<User> u = a.getMyEmployee(Integer.parseInt(allMatches.get(0)));
			ArrayList<Reimburse> re = new ArrayList<Reimburse>();
			ObjectMapper mapper = new ObjectMapper();
	
			System.out.println(u);
			for(User ee: u) {
				re.addAll(a.getReimburse(ee));
			}
			;
			String json = "["+ mapper.writeValueAsString(u);
			if(re!=null) {
				for(Reimburse r:re) {
					json = json + "," +mapper.writeValueAsString(r);
				}
			}
			json = json + "]";
			resp.getWriter().write(json);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().write("{\"session\":null}");
		}
			
			
			
		}
		else if (act.equals("emp")) {
			try {

				accountDAOImple a = new accountDAOImple();
				ArrayList<User> u = a.getAllUser();
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(u);
				resp.getWriter().write(json);
			} catch (Exception e) {
				e.printStackTrace();
				resp.getWriter().write("{\"session\":null}");
			}
		} else if (act.equals("resolved")) {
			try {

				accountDAOImple a = new accountDAOImple();
				ArrayList<Reimburse> r = a.getCompleteRe();
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(r);
				resp.getWriter().write(json);
			} catch (Exception e) {
				e.printStackTrace();
				resp.getWriter().write("{\"session\":null}");
			}
		} else {
			resp.getWriter().write("{\"session\":null}");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public static String getBody(HttpServletRequest request) throws IOException {

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}
}
