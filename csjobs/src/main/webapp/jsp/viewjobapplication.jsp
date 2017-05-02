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
		
		<h4>Job Application: </h4>
		
		<strong>Job title:</strong> <br />
		${sessionScope['job_title']}
		<br /><br />
		
		<strong>Current job details:</strong> <br />
		<p>
			<strong>Current job institution: </strong> ${sessionScope['application'].currentJobInstitution }
		</p>
		
		<p>
			<strong>Current job title: </strong> ${sessionScope['application'].currentJobTitle }
		</p>
		
		<p>
			<strong>Current job year: </strong> ${sessionScope['application'].currentJobYear }
		</p>
		
		<strong>Degree details:</strong> <br />
		<table border = "1">
			<tr>
				<th>Degree</th>
				<th>School</th>
				<th>Year</th>
			</tr>
			
			<c:forEach var = "degree" items = "${sessionScope['application_degrees']}" >
				<tr>
					<td>${degree.name }</td>
					<td>${degree.school }</td>
					<td>${degree.year }</td>				
				</tr>
			</c:forEach>
			
		</table>
		
		<br />
		
		<strong>Uploaded file details:</strong> <br />
		<ul>
			<li>
				<b>CV:</b> <br />
				<a href = "download.html?filename=${sessionScope['application'].cv.name }">${sessionScope['application'].cv.name }</a>
				<br /><br />
			</li>
			
			<li>
				<b>Teaching statement:</b> <br />
				<a href = "download.html?filename=${sessionScope['application'].teachingStatement.name }">${sessionScope['application'].teachingStatement.name }</a>
				<br /><br />
			</li>
			
			<li>
				<b>Research statement:</b> <br />
				<a href = "download.html?filename=${sessionScope['application'].researchStatement.name }">${sessionScope['application'].researchStatement.name }</a>
			</li>
		</ul>
		
		
	</body>
</html>