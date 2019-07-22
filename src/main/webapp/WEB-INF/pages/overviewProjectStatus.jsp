<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Project Details</title>
    </head>
    <body background="<%=request.getContextPath()%>/resources/images/bg2.jpg">
    <%@include file="header.jsp" %>
        <div align="center">

            <form action="downloadOverviewProjectStatus" method="post">
                <input type="hidden" name="project_id" value="${project_id}">
                <input type="submit" value="Download Task Report" > 
            </form>
            <table border="1">
                <th>Developer Name</th>
                <th>Estimate Days</th>
                <th>Actual Days</th>
                <th>Delay Days</th>
                    <c:forEach var="dTO" items="${taskDTOs}">
                    <tr>

                        <td>${dTO.emp_name}</td>
                        <td>${dTO.estimateDays}</td>
                        <td>${dTO.actualDays}</td>
                        <td>${dTO.delayDays}</td>
                    </tr>
                </c:forEach>
            </table>
            <form action="/PMS-1.2/ManagerProjectDetBack" method="post">
                <input type="submit" value="Back"/>
            </form>
        </div>
          
    </body>

</html>