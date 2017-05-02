<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS Jobs - Admin</title>
</head>
<body>
	<h2>CS Jobs </h2>

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

	<h3>Add job</h3>


	<form action="addJob.html" method="POST">

		<table border="1">
		
			<tr>
				<td><b>Title</b></td>
				<td><input type="text" name="jobTitle" /></td>
			</tr>

			<tr>
				<td><b>Description</b></td>
				<td><textarea rows="5" cols="50" name="jobDescription"></textarea>
				</td>
			</tr>

			<tr>
				<td><b>Publish date</b></td>
				<td><input type="text" name="publishDate" /> (Format: M/d/yyyy)</td>
			</tr>

			<tr>
				<td><b>Close date</b></td>
				<td><input type="text" name="closeDate" /> (Format: M/d/yyyy)</td>
			</tr>

			<tr>
				<td><b>Chair committee</b></td>
				<td>
					<select name="chairCommittee">
						<c:forEach items="${sessionScope['reviewers']}" var="reviewer">
							<option value="${reviewer.id}">${reviewer.firstName} ${reviewer.lastName} </option>
						</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<td><b>Committee members</b></td>
				<td>
					<c:forEach items="${sessionScope['reviewers']}" var="reviewer">
						<input type="checkbox" name="reviewer_group" value="${reviewer.id}" />${reviewer.firstName} ${reviewer.lastName} <br />
					</c:forEach>
			</tr>

			<tr>
				<td><input type="submit" value="Create" /></td>
			</tr>
		</table>
	</form>

</body>
</html>
