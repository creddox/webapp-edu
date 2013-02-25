package edu.web_app.beans;

import java.util.Date;
import java.util.UUID;
import javax.servlet.ServletContext;

public class SessionBean {
	private Date creationDate;
	private UUID sessionKey;
	private ServletContext context;
	
	public SessionBean() {
		this.creationDate = new Date();
	}

	public UUID getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(UUID sessionKey) {
		this.sessionKey = sessionKey;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}	
}
