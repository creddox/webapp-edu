package edu.web_app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

public class DbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DbServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}
	
	public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head><title>DB query</title></head>");
		out.println("<body>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>User</th><th>Password</th>");
		out.println("</tr>");
		
		try {
			//Get context configuration from context.xml
			InitialContext appContext = new InitialContext();
			
			//Use the credentials defined in context.xml and web.xml for
			//PostgreSQL database.
			DataSource ds = (DataSource) appContext.lookup("java:comp/env/jdbc/UserDB");
			
			//Create connection and execute a query, write results into rs
			Connection conn = ds.getConnection();			
			Statement query = conn.createStatement();			
			ResultSet rs = query.executeQuery("SELECT user_name, password FROM app.users");
			
			//write the ResultSet to out
			while (rs.next()) {
				out.println("<tr>");
				out.println("<td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td>");
			}
			
			//close statement and connection
			rs.close();
			query.close();
		} catch (NamingException e) {
			System.err.println("No context available!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Error when establishing connection!");
			e.printStackTrace();
		}
		
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}
