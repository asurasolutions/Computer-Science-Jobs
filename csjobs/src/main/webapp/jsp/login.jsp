<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CS Jobs Login Page</title>
	</head>
	
	<body>
	
		<h3>CS Jobs User Login</h3>
		
		<!-- <form action = "login.html" method = "post"> -->
		
		<form name="loginForm" action = "j_spring_security_check" method = "POST">
			
			<strong>Enter email address: </strong><br />
			<!-- <input type = "text" name = "emailid" /> -->
			<input type="text" class="form-control" id="username" name = "j_username" >
			
			<br /><br />
			
			<strong>Enter password: </strong><br />
			<!-- <input type = "password" name = "password" /> -->
			<input type="password" class="form-control" id="password" name = "j_password" >
			
			<br /><br />
			
			<input type = "submit"  value = "Login"/>
			
			<br />
			<p style = "color : red;">${loginerror }</p>
		</form>		
		
	</body>
</html>