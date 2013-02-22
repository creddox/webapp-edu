package edu.web_app.beans;

import java.sql.*;
import java.util.Properties;


public class DatabaseBean {
	private String user = "";
	private String password = "";
	private String connUrl = "";
	private Connection db;
	
	public DatabaseBean() {
	}	
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConnUrl() {
		return connUrl;
	}

	public void setConnUrl(String connUrl) {
		this.connUrl = connUrl;
	}

	public void connectDatabase() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		
		Properties props = new Properties();
		props.setProperty("user", this.getUser());
		props.setProperty("password", this.getPassword());
		
		this.db = DriverManager.getConnection(this.getConnUrl(), props);
	}
	
	public ResultSet executeStatement(String query) throws SQLException {
		Statement st = db.createStatement();
		
		ResultSet returnSet = st.executeQuery(query);
		return returnSet;
	}
}
