<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CS Jobs Application Page</title>
	</head>
	
	<body>
	
		<h3>CS Jobs Application Page</h3>
		
		<table width = "100%">
			<tr>
				<th style = "text-align: left;">
					Hi ${sessionScope['activeUser'].firstName},
				</th>
				<th style = "text-align: right;">
					<form action = "logout.html" method = "POST">
						<input type = "submit" value = "Logout" />
					</form>
				</th>
			</tr>
		</table>
		
		<br />
		
		<strong>Applying for a job is a 3-step process, as below:</strong> <br />
		Step-1: Enter your job details. <br />
		Step-2: Enter your list of colleges and advanced degrees.<br />
		Step-3: Apply for a job.<br />
		
		<br />
		
		<h4>Step-3: Apply for a job.</h4>
		
		<strong>Job title:</strong> <br />
		${sessionScope['applyForJob'].title }
		<br /><br />
		
		<strong>Current job details:</strong> <br />
		<p>
			<strong>Current job institution: </strong> ${sessionScope['currentJobInstitution'] }
		</p>
		
		<p>
			<strong>Current job title: </strong> ${sessionScope['currentJobTitle'] }
		</p>
		
		<p>
			<strong>Current job year: </strong> ${sessionScope['currentJobYear'] }
		</p>
		
		<strong>Degree details:</strong> <br />
		<table border = "1">
			<tr>
				<th>Degree</th>
				<th>School</th>
				<th>Year</th>
			</tr>
			
			<c:forEach var = "degree" items = "${sessionScope['degrees']}" >
				<tr>
					<td>${degree.name }</td>
					<td>${degree.school }</td>
					<td>${degree.year }</td>				
				</tr>
			</c:forEach>
			
		</table>
		
		<br />
		
		<form action = "submitJobApplication.html" method = "POST">
			<input type = "submit" value = "Apply for job" />
		</form>
		
	</body>
</html>