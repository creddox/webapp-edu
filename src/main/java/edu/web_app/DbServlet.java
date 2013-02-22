package edu.web_app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}
