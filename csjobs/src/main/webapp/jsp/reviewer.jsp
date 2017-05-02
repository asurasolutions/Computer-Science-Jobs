<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CS Jobs Reviewer Page</title>
	</head>
	
	<body>
	
		<h3>CS Jobs Reviewer Page</h3>
		
		<table width = "100%">
			<tr>
				<th style = "text-align: left;">
					Hi ${sessionScope['activeUser'].firstName},
				</th>
				<th style = "text-align: right;">
					<!--  <form action = "logout.html" method = "POST">
						<input type = "submit" value = "Logout" />
					</form> -->
					
					<form action = "j_spring_security_logout" method = "GET">
					 	<input type="submit" id = "logout" value = "logout" />
					 </form>
				</th>
			</tr>
		</table>
		
		<h4>This is the reviwer's page ! </h4>
		
	</body>
</html>