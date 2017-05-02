<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS Jobs - Admin</title>
</head>
<body>
	<h2>CS Jobs Edit Job</h2>

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

	<h3>Edit job</h3>


	<form action="editJob.html" method="POST">

		<table border="1">
		
			<tr>
				<td><b>Title</b></td>
				<td><input type="text" name="jobTitle" value = "${job.title }"/></td>
			</tr>

			<tr>
				<td><b>Description</b></td>
				<td>
					<textarea rows="5" cols="50" name="jobDescription" >${job.description }</textarea>
				</td>
			</tr>

			<tr>
				<td><b>Publish date</b></td>
				<td>
					<input type="text" name="publishDate" value = "<fmt:formatDate value="${job.publishDate}" pattern="M/d/yyyy" />"> (Format: M/d/yyyy)
				</td>
			</tr>

			<tr>
				<td><b>Close date</b></td>
				<td>
					<input type="text" name="closeDate" value = "<fmt:formatDate value="${job.closeDate}" pattern="M/d/yyyy" />"> (Format: M/d/yyyy)	
				</td>
			</tr>

			<tr>
				<td><b>Chair committee</b></td>
				<td>
					<select name="chairCommittee">
						<c:forEach items="${sessionScope['reviewers']}" var="reviewer">
						
							<c:choose>
								<c:when test="${reviewer.id == job.committeeChair.id }">
									<option value="${reviewer.id}" selected>${reviewer.firstName} ${reviewer.lastName} </option>
								</c:when>
								<c:otherwise>
									<option value="${reviewer.id}">${reviewer.firstName} ${reviewer.lastName} </option>
								</c:otherwise>
							</c:choose>
						
						</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<td><b>Committee members</b></td>
				<td>
					<c:forEach items="${sessionScope['reviewers']}" var="reviewer">
					
						<c:set var="check" scope="session" value="0"/>
						<c:forEach items = "${job.committeeMembers }" var = "member">
						
							<c:if test="${reviewer.id == member.id }">
								<c:set var="check" scope="session" value="1"/>
							</c:if>

						</c:forEach>
						
						<c:choose>
							<c:when test="${check == 1 }">
								<input type="checkbox" name="reviewer_group" value="${reviewer.id}" checked />${reviewer.firstName} ${reviewer.lastName} <br />							
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="reviewer_group" value="${reviewer.id}" />${reviewer.firstName} ${reviewer.lastName} <br />
							</c:otherwise>
						</c:choose>
						
					</c:forEach>
			</tr>

			<tr>
				<td><input type="submit" value="Save" /></td>
			</tr>
		</table>
	</form>

</body>
</html>
