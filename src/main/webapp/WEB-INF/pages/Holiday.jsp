<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="adminback" method="post">
<input type="submit" value="Back">
</form>
	<form:form action="Addholiday" commandName="holiday" method="post">
		<div align="center">
			<table>
				<tr>
					<form:hidden path="daysId" />
					<td><form:input path="hDays" type="date" /></td>
					<td><input type="submit" value="Add" /></td>
				</tr>

			</table>
			<table border="1">
				<th>Holidays</th>
				<th>Action</th>

				<c:forEach var="holi" items="${listholiDays}">
					<tr>

						<td>${holi.hDays}</td>
						<td><a href="deleteHoliday?daysId=${holi.daysId}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form:form>
</body>
</html>