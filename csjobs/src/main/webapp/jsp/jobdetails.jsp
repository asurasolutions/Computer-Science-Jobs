<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CS Jobs Details</title>
	</head>
	
	<body>
	
		<h3>CS Jobs Details</h3>
		
		<h4>Job Details</h4>
		
		<strong>Job title:</strong> <br />
		${sessionScope['selectedJob'].title}
		<br /><br />
		
		<strong>Publish date:</strong> <br />
		<c:choose>
			<c:when test = "${empty sessionScope['selectedJob'].publishDate }">
				No publish date.
			</c:when>
			<c:otherwise>
			<fmt:formatDate value="${sessionScope['selectedJob'].publishDate}" pattern="M/d/yyyy" />
			</c:otherwise>
		</c:choose>	
		<br /><br />			
		
		<strong>Job description:</strong> <br />
		${sessionScope['selectedJob'].description}
		<br /><br />
		
		<strong>Close date:</strong> <br />
		<c:choose>
			<c:when test = "${empty sessionScope['selectedJob'].closeDate }">
				No close date.
			</c:when>
			<c:otherwise>
			<fmt:formatDate value="${sessionScope['selectedJob'].closeDate}" pattern="M/d/yyyy" />
			</c:otherwise>
		</c:choose>
		
		<br /><br />
		
	</body>
</html>