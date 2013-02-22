package edu.web_app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.web_app.beans.*;

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
		
		DatabaseBean dbCon = new DatabaseBean();
		
		dbCon.setUser("postgres");
		dbCon.setPassword("");
		dbCon.setConnUrl("jdbc:postgresql://192.168.1.11:5443/web-app");
		
		try {
			dbCon.connectDatabase();
			
			ResultSet rs = dbCon.executeStatement("SELECT * FROM app.users");
			 
			 while (rs.next()) {
					out.println("<tr>");
					out.println("<td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td>");
					out.println("</tr>");
				}
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC-Treiberproblem!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("SQL-Query Fehler!");
			e.printStackTrace();
		}
		
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}
