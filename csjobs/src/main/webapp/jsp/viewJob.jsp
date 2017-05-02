<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CSJobs - Job</title>
</head>
<body>
	<h2>CS Jobs </h2>
	<h3>${job.title}</h3>
	<p>
		<b>Posted on:</b>
		<c:choose>
			<c:when test = "${empty job.publishDate}">
				No publish date.
			</c:when>
			<c:otherwise>
				<fmt:formatDate value="${job.publishDate}" pattern="M/d/yyyy" />
			</c:otherwise>
		</c:choose>
	</p>
	<p>
		<b>Close on:</b>
		<c:choose>
			<c:when test = "${empty job.closeDate}">
				No close date.
			</c:when>
			<c:otherwise>
				<fmt:formatDate value="${job.closeDate}" pattern="M/d/yyyy" />
			</c:otherwise>
		</c:choose>
	</p>
	<p>
		<b>Job Description:</b>
	</p>
	<p>${job.description}</p>

	<p>
		<b>Review committee chair:</b> 
		<i>${job.committeeChair.firstName} ${job.committeeChair.lastName} </i>
	</p>
	
	<p>
		<b>Review committee members:</b>
		<ul>
			<c:forEach items="${job.committeeMembers}" var="member">
				<li>
					<i>${member.firstName} ${member.lastName}</i>
				</li>
			</c:forEach>
		</ul>
	</p>
</body>
</html>
