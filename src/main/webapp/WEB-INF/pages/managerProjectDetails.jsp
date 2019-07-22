<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body background="<%=request.getContextPath()%>/resources/images/bg2.jpg">
    <%@include file="header.jsp" %>
        <div align="center">  <table border="1">
                <th>Project Name</th>
                <th>View Details</th>
                <th>Overview</th>
                    <c:forEach var="pd" items="${listProjectName}">
                    <tr>

                        <td>${pd.project_Name}</td>
                        <td><a href="viewProject?project_id=${pd.project_id}">View Details</a></td>  
                        <td><a href="overviewProject?project_id=${pd.project_id}">Overview</a></td>
                    </tr>
                </c:forEach>
            </table><br/>
            <form action="/PMS-1.2/ManagerDashboardBack" method="post">
                <input type="submit" value="Back"/>
            </form>
        </div>
        
         
    </body>
</html>

