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
		//Get output stream and set output as HTML		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//variable containing UID
		int uid = -1;	//provides safe default value

		//Get the necessary login information via parameters.
		String loginName = "";
		loginName = request.getParameter("name");

		String password = "";
		password = request.getParameter("password");
		
		//are the parameters empty? If yes redirect to login.html		
		if (loginName == null || password == null) {
			this.getServletContext().getRequestDispatcher("/login.html").forward(request, response);
		}
		
		//Parameters are not empty, query the database for name and password.
		try {
			uid = this.getUID(loginName, password);						
		} catch (NamingException e) {
			out.println("Error when accessing database.<br/>");
			out.println(e.getMessage() + "<br/>");
			out.println("A complete stacktrace has been created in the logs.");
			e.printStackTrace();
		} catch (SQLException e) {
			out.println("Error when performing a query on the database.<br/>");
			out.println(e.getMessage() + "<br/>");
			out.println("A complete stacktrace has been created in the logs.");
			e.printStackTrace();
		}
		
		out.println("<html>");
		out.println("<head><title>User validation</title></head>");
		out.println("<bod>");
		
		if (uid < 0) {
			out.println("<b>No valid credentials provided.");
		} else {
			out.println("<b>Credentials validated! UID: " + uid + "</b>");
		}
		
		out.println("</body>");
		out.println("</html>");
	}

	//Checks the user credentials and returns his UID from the database. Returns -1 if user
	//did not provide valid credentials.
	protected int getUID(String loginName, String password) throws NamingException, SQLException {
		InitialContext appContext;
		int returnValue = -1;
		ResultSet rs = null;
		PreparedStatement query = null;

		//Initializing the connection
		appContext = new InitialContext();
		DataSource ds = (DataSource) appContext.lookup("java:comp/env/jdbc/UserDB");			
		Connection conn = ds.getConnection();

		//Building query and preparing statement
		String queryString = "";
		queryString = "SELECT * FROM app.users WHERE user_name= ? AND password = ? ";
		query = conn.prepareStatement(queryString);

		//Inserting values into query
		query.setString(1, loginName);
		query.setString(2, password);

		//Execute statement
		rs = query.executeQuery();
		
		while (rs.next()) {
			if(rs.getString(2).matches(loginName) && rs.getString(3).matches(password)) {
				returnValue = rs.getInt(1);
			}
		}

		rs.close();
		query.close();

		return returnValue;
	}

}
