<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member page</title>
</head>
<body>
	<% int uid = -1;
	
	if (request.getSession().getAttribute("uid") != null) {
		uid = (Integer) request.getSession().getAttribute("uid");
	}
	
	if (uid < 0) {
	%>
	<b>This member page is only for validated users! Come back when you have logged in.</b>
	<%
	} else {
	%>
	<b>This was a triumph. I'm making a note here, huge success.</b>
	<form action="/web-app/logout" method="post">
		<input type="submit" name="logout" value="Logout">
	</form>
	<%	
	}
	%>
</body>
</html>