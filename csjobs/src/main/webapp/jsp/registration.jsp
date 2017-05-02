<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CS Jobs Home Page</title>
	</head>
	
	<body>
	
		<h3>Register an account on CS Jobs Portal</h3>
		
		<form action = "register.html" method = "post">
		
			<strong>Enter first name: </strong><br />
			<input type = "text" name = "firstname" />
			
			<br /><br />
			
			<strong>Enter last name: </strong><br />
			<input type = "text" name = "lastname" />
			
			<br /><br />
			
			<strong>Enter email address: </strong><br />
			<input type = "text" name = "emailid" />
			
			<br /><br />
			
			<strong>Enter password: </strong><br />
			<input type = "password" name = "password" />
			
			<br /><br />
			
			<strong>Re-enter password: </strong><br />
			<input type = "password" name = "password2" />
			
			<br /><br />
			
			<strong>Enter phone number: </strong><br />
			<input type = "text" name = "phone" />
			
			<br /><br />
			
			<strong>Enter address: </strong><br />
			<input type = "text" name = "address" />
			
			<br /><br />
			
			<input type = "submit"  value = "Register"/>
		
		</form>
		
		<br />
		
		<div style = "color : red;">
			${allinputerror }	
		</div>
		
		<div style = "color : green;">
			${success }	
		</div>
		
		<br /><br />
		Click <a href = "login.html">here</a> to login !
		
	</body>
</html>