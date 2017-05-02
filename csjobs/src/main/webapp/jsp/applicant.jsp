<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CS Jobs Applicant Page</title>
	</head>
	
	<body>
	
		<h3>CS Jobs Applicant Page</h3>
		
		<table width = "100%">
			<tr>
				<th style = "text-align: left;">
					Hi ${sessionScope['activeUser'].firstName},
				</th>
				<th style = "text-align: right;">
					<!-- <form action = "logout.html" method = "POST">
						<input type = "submit" value = "Logout" />
					</form> -->
					
					<form action = "j_spring_security_logout" method = "GET">
					 	<input type="submit" id = "logout" value = "logout" />
					 </form>
				</th>
			</tr>
		</table>
		
		<h4>The applicant has applied for following jobs</h4>
		
		<table border = "1">
			<tr>
				<th>Sr. no</th>
				<th>Job title</th>
				<th>View application</th>
			</tr>
			
			<c:set var = "srno" scope = "session" value = "1" />
			<c:forEach var = "application" items = "${sessionScope['userApplications']}" >
				<tr>
					<td>${srno }</td>		
					<td> ${application.job.title }</td>
					<td><a href = "viewApplication.html?user_id=${sessionScope['activeUser'].id }&job_id=${application.job.id }&job_title=${application.job.title }">view</a></td>
				</tr>
				<c:set var = "srno" scope = "session" value = "${srno + 1 }" />	
			</c:forEach>
		</table>
		
		<br /><br />
		
		<c:set var = "srno" scope = "session" value = "1" />
		<h4>The applicant may apply for following jobs</h4>
		<table border = "1">
			<tr>
				<th>Sr. no</th>
				<th>Job title</th>
				<th>Post date</th>
				<th>Close date</th>
				<th>Apply</th>
			</tr>
			
			<c:set var = "srno" scope = "session" value = "1" />
			
			<c:forEach var = "job" items = "${sessionScope['eligibleJobs']}" >
				<tr>
					<td>${srno }</td>				
					<td> ${job.title }</td>
					<td>${job.publishDate }</td>
					<td>
						<c:choose>
							<c:when test = "${empty job.closeDate }">
								No close date.
							</c:when>
							<c:otherwise>
								${job.closeDate }
							</c:otherwise>
						</c:choose>
					
					</td>
					<td><a href = "applyForJob.html?title=${job.title }&id=${job.id }">Apply</a></td>
				</tr>
				<c:set var = "srno" scope = "session" value = "${srno + 1 }" />	
			</c:forEach>

		</table>
		
	</body>
</html>