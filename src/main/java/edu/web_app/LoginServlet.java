package edu.web_app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//In this project, only POST will be used. Should something send a request via GET,
		//use the POST method instead.
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get output stream
		
		PrintWriter out = response.getWriter();
		
		//Get the necessary login information via parameters.
		String loginName = "";
		loginName = request.getParameter("name");
		
		String password = "";
		password = request.getParameter("password");
		
		this.doQuery(loginName, password, out);
	}
	
	//Checks the user credentials and writes them in the session context. Returns true, if
	//user has provided valid credentials and returns false, if user has not.
	protected boolean doQuery(String loginName, String password, PrintWriter out) {
		InitialContext appContext;
		boolean returnValue = false;
		
		try {
			//Initializing the connection
			appContext = new InitialContext();
			DataSource ds = (DataSource) appContext.lookup("java:comp/env/jdbc/UserDB");			
			Connection conn = ds.getConnection();
			
			//Building query
			String queryString = "";
			queryString = "SELECT * FROM app.users WHERE user_name= ? AND password = ? ";
			PreparedStatement query = conn.prepareStatement(queryString);
			
			//Inserting values into query
			query.setString(1, loginName);
			query.setString(2, password);
			
			//Execute statement
			ResultSet rs = query.executeQuery();
			
			while (rs.next()) {
				out.println("UID: " + rs.getInt(1));
				out.println("Username: " + rs.getString(2));
				out.println("Password: " + rs.getString(3));
			}
			
			rs.close();
			query.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnValue;
	}

}
