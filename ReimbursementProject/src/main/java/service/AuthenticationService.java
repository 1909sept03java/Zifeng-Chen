package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.*;
import dao.accountDAOImple;
import util.ConnectionUtil;

public class AuthenticationService {

	public User authenticateUser(Credentials creds) {
		User u = null;
		accountDAOImple a = new accountDAOImple();
		ArrayList<User> us = a.getAllUser();

		for (User e : us) {

			String user = creds.getUsername();
			String pass = creds.getPassword();
			if (pass.contentEquals(e.getPassword()) && user.contentEquals(e.getEmail())) {
				System.out.println("run");
				u = e;
			}

		}
		if (u == null)
			System.out.println("Not User Found");
		return u;
	}

}