<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CS Jobs Application</title>
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
		
		<h4>Step-2: Enter your list of colleges and advanced degrees.</h4>
		
		<strong>Job title:</strong> <br />
		${sessionScope['applyForJob'].title }
		<br /><br />
			
		<strong>Degrees held by the applicant: </strong> <br />
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
		
		<br /><br />
			
		<form action = "addDegree.html" method = "POST">
			<strong>Add degree: </strong><br />
			<table border = "1" >
				<tr>
					<th>Degree</th>
					<th>University</th>
					<th>Year</th>
				</tr>
				
				<tr>
					<td><input type = "text" name = "degreename" /></td>
					<td><input type = "text" name = "universityname" /></td>
					<td><input type = "text" name = "completionyear" /></td>
				</tr>			
			
				<tr>
					<td></td>
					<td><input type = "submit"  value = "Add degree" name = "buttonname"/></td>
				</tr>
			</table>
			
		</form>
		
		<br />
		
		<div style = "color : red;">
			${allinputerror }	
		</div>
		
		<br />

		<form action = "prepareJobApplication.html" method = "POST">
			<input type = "submit" value = "Next" />
		</form>
		
	</body>
</html>