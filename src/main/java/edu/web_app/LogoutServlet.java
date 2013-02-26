package edu.web_app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.web_app.beans.SessionBean;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LogoutServlet() {
        super();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String sessionKey = "";
		Cookie[] cookies = request.getCookies();
		
		//Search for "sessionID" cookie
		for (int i = 0; i < cookies.length; i = i + 1) {
			if(cookies[i].getName().matches("sessionID")) {
				sessionKey = cookies[i].getValue();
				
				//Delete cookie
				cookies[i].setMaxAge(0);
			}
		}
		
		//Does the user have a sessionID cookie?
		if (!sessionKey.matches("")) {
			
			//Is there a matching id in application context?
			if (this.getServletContext().getAttribute(sessionKey) != null) {
				
				//We retrieved the unique identifier above, so use that to get our SessionBean object
				SessionBean session = (SessionBean) this.getServletContext().getAttribute(sessionKey);
				
				//Set object to null to let it be automatically deleted
				session = null;
				
				//Delete unique identifier from application context
				this.getServletContext().removeAttribute(sessionKey);
				
				//Print confirmation message and redirect to login.html after 5 seconds.		
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Logout successful</title>");
				out.println("<meta http-equiv=\"refresh\" content=\"5;/web-app/login.html\"/>");
				out.println("</head>");
				out.println("<body>");
				out.println("<b>You have been successfully logged out! Redirecting to login page in 5" +
						"seconds</b>");
				out.println("</body>");
				out.println("</html>");
			} else {
				//Output the same error message as below.
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Logout unsuccessful</title>");
				out.println("<meta http-equiv=\"refresh\" content=\"5;/web-app/login.html\"/>");
				out.println("</head>");
				out.println("<body>");
				out.println("<b>You couldn't be successfully logged out. Redirecting to login page in" +
						" 5 seconds</b>");
				out.println("</body>");
				out.println("</html>");
			}
			
		} else {
			//Output error message and redirect to login page.
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Logout unsuccessful</title>");
			out.println("<meta http-equiv=\"refresh\" content=\"5;/web-app/login.html\"/>");
			out.println("</head>");
			out.println("<body>");
			out.println("<b>You couldn't be successfully logged out. Redirecting to login page in" +
					" 5 seconds</b>");
			out.println("</body>");
			out.println("</html>");
		}
		
		
	}

}
