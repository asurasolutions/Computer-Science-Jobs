<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CS Jobs Administration Page</title>
	</head>
	
	<body>
	
		<h3>CS Jobs Administration Page</h3>
		
		<table width = "100%">
			<tr>
				<th style = "text-align: left;">
					Hi ${sessionScope['activeUser'].firstName},
				</th>
				<th style = "text-align: right;">
					<!-- <form action = "logout.html" method = "POST">
						<input type = "submit" value = "Logout" />
					</form>  -->
					
					 <form action = "j_spring_security_logout" method = "GET">
					 	<input type="submit" id = "logout" value = "logout" />
					 </form>
					
				</th>
			</tr>
		</table>
		
		<h4>Admin Home</h4>
		
		<a href = "addJob.html">Create New Job</a>
		<br /><br />
		
		<table border = "1" >
			<tr>
				<td>Job</td>
				<td>Publish date</td>
				<td>Close date</td>
				<td>Operations</td>
			</tr>
		
			<c:forEach items="${sessionScope['openJobsList']}" var="job">
				<tr>
					<td>
						<a href="viewJob.html?id=${job.id}">${job.title}</a>	
					</td>
					<td>
						<c:choose>
							<c:when test = "${empty job.publishDate }">
								No publish date.
							</c:when>
							<c:otherwise>
								
								<fmt:formatDate value="${job.publishDate}" pattern="M/d/yyyy" />
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test = "${empty job.closeDate }">
								No close date.
							</c:when>
							<c:otherwise>
								
								<fmt:formatDate value="${job.closeDate}" pattern="M/d/yyyy" />
							</c:otherwise>
						</c:choose>
					</td>
					<td><a href = "editJob.html?id=${job.id }">Edit</a></td>
				</tr>
			</c:forEach>	

	</table>
		
	</body>
</html>