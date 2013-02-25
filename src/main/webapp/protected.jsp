<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.web_app.beans.SessionBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member page</title>
</head>
<body>
	<% Cookie[] cookies = request.getCookies();
	String uuid = "";
	
	//Determine whether or not one of the sent cookies is actually a SessionBean object.
	for (int i = 0; i < cookies.length; i++) {
		if (application.getAttribute(cookies[i].getValue()) != null) {
			uuid = cookies[i].getValue();
		}
	}
	
	if (uuid.matches("")) {
	%>
	<b>You need to login to view a secret sentence!</b>
	<%
	} else {
		SessionBean mkSession = (SessionBean) application.getAttribute(uuid);
	%>
	<b>This was a triumph. I'm making a note here, huge success.</b><br/>
	<%=mkSession.getCreationDate() %><br/>
	<%=mkSession.getSessionKey().toString() %>
	<%
	}	
	%>
</body>
</html>