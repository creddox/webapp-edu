package edu.web_app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
	}
	
	protected void doQuery(String query) {
		
	}

}
