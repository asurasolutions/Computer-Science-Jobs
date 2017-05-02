<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CS Jobs Home Page</title>
	</head>
	
	<body>
	
		<h3>CS Jobs Home Page</h3>
		
		<h4>Below is the list of open jobs:</h4>
		<table border = "1" >
			<tr>
				<th>Sr no.</th>
				<th>Title</th>	
			</tr>
			
			<c:set var = "srno" scope = "session" value = "1" />
			
			<c:forEach var = "openJob" items = "${sessionScope['openJobsList']}" >
				<tr>
					<td>${srno }</td>				
					<td>
						<a href = "getJobDetails.html?jobtitle=${openJob.title }&id=${openJob.id }">${openJob.title }</a>					
					</td>
				</tr>
				<c:set var = "srno" scope = "session" value = "${srno + 1 }" />	
			</c:forEach>
		</table>
		
		<br /><br />
		
		<a href = "login.html">login</a> | <a href = "register.html">Register</a>			
		
	</body>
</html>