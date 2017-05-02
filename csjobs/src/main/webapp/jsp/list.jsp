<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS Jobs</title>
</head>
<body>
	<h2>CS Jobs</h2>

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

	<h3>Open Job Postions</h3>
	<ul>
		<c:forEach items="${openJobs}" var="job">
			<li><a href="<c:url value='/job/view.html?id=${job.id}' />">${job.title}</a></li>
		</c:forEach>
	</ul>
</body>
</html>
