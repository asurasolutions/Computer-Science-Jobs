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
		
		<h4>Step-1: Enter your job details</h4>
		
		<form action = "addJobDetails.html" method = "POST" enctype="multipart/form-data">
		
			<strong>Job title:</strong> <br />
			${sessionScope['applyForJob'].title }
			<br /><br />
			
			<strong>Current job institution:</strong> <br />
			<input type = "text" name = "currentJobInstitution" />
			<br /><br />
			
			<strong>Current job title:</strong> <br />
			<input type = "text" name = "currentJobTitle" />
			<br /><br />
			
			<strong>Current job year:</strong> <br />
			<input type = "text" name = "currentJobYear" />
			<br /><br />
			
			<strong>Upload CV:</strong> <br />
			<input type = "file" name = "CVfile" /> 
			<br /><br />
			
			<strong>Upload teaching statement:</strong> <br />
			<input type = "file" name = "TSfile" /> 
			<br /><br />
			
			<strong>Upload research statement:</strong> <br />
			<input type = "file" name = "RSfile" /> 
			<br /><br />
			
			<input type = "submit" value = "Next" />
		
		</form>
		
		<br />
		
		<div style = "color : red;">
			${allinputerror }	
		</div>
		
	</body>
</html>