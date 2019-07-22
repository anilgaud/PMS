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
     <jsp:include page="header.jsp"/>
        <div align="center">
            <form action="downloadProjectStatus" method="post">
                <input type="hidden" name="project_id" value="${project_id}">
                <input type="submit" value="Download Project Report" > 
            </form>
            <table border="1">
                <th>Developer Name</th>
                <th>Task Name</th>
                <th>Task Type</th>
                <th>Task Start Date</th>
                <th>Task End Date</th>
                <th>Task Status</th>
                <th>Task Submit Date</th>
                <th>Estimate Days</th>
                <th>Actual Days</th>
                <th>Delay Days</th>
                    <c:forEach var="dTO" items="${taskDTOs}">
                    <tr>

                        <td>${dTO.emp_name}</td>
                        <td>${dTO.task_Name}</td>
                        <td>${dTO.task_Type}</td>
                        <td>${dTO.tStartDate}</td>
                        <td>${dTO.tEndDate}</td>
                        <td>${dTO.status}</td>
                        <td>${dTO.tsubDate}</td>
                        <td>${dTO.estimateDays}</td>
                        <td>${dTO.actualDays}</td>
                        <td>${dTO.delayDays}</td>
                    </tr>
                </c:forEach>
            </table>
            <form action="//ManagerProjectDetBack" method="post">
                <input type="submit" value="Back"/>
            </form>
        </div>
        
        
    </body>
</html>