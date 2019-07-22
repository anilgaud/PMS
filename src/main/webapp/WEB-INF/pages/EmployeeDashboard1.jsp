<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Insert title here</title>
    </head>
    <body  background="<%=request.getContextPath()%>/resources/images/bg2.jpg" >
        <%@include file="header.jsp" %>

        <form:form action="/PMS-1.2/Empedit" method="post" modelAttribute="employee" commandName="employee">
            <form:hidden path="email" />
            <input type="submit" value="Edit Profile">
        </form:form>
        <form:form action="/PMS-1.2/editTask" method="POST"
                   commandName="taskdetails" modelAttribute="employee">


            <form:hidden path="email" />
            <%-- <form:hidden path="task_id" />
            <form:hidden path="task_Type" />
            <form:hidden path="task_Name" />
            <form:hidden path="tStart_Time" />
            <form:hidden path="tEnd_Time" /> --%>
            <%-- <form:input path="task_Name" /> --%></br></br>
            <table border="1" align="center" width="800" height="100">
                <th style="color: red">Project Name</th>
                <th style="color: red">Task Type</th>
                <th style="color: red">Task Name</th>
                <th style="color: red">Start Date</th>
                <th style="color: red">End Date</th>
                <th style="color: red">Manager Name</th>
                <th style="color: red">Status</th>
                <th style="color: red">Action</th>
                    <%--                <td><input type="submit" value="Save Changes"></td>  
                                    <td><a href="editlink1?tid=${tlist.task_id}&mail=${mail}">Edit</a></td> --%>
            </tr>

            <%--</c:forEach>--%>
            <c:forEach var="taskObj" items="${tobj}">
                <tr style="color: black">


                    <td>${taskObj.project_Name}</td>
                    <td>${taskObj.task_Type}</td>
                    <td>${taskObj.task_Name}</td>
                    <td>${taskObj.tStartDate}</td>
                    <td>${taskObj.tEndDate}</td>
                    <td>${taskObj.name}</td>
                    <td>${taskObj.status}</td>

                    <td><a href="editlink1?tid=${taskObj.id}&mail=${taskObj.email}">Edit</a></td>  
                </tr>
            </c:forEach>
            <tr>

                <!--  <td><input type="submit" value="Done"></td> -->
            </tr>
        </table>
    </form:form>

    <form:form action="/PMS-1.2/Empedit" method="post"  modelAttribute="employee" commandName="employee">
        <form:hidden path="email" />
        <input type="submit" value="Back">
    </form:form>


</body>
</html>