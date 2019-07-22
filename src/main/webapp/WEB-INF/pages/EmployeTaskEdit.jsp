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
    <body
        background="<%=request.getContextPath()%>/resources/images/bg2.jpg">
        <%@include file="header.jsp" %>
        <div align="center">
            <form:form action="editTask" method="post"
                       modelAttribute="taskdetails" commandName="taskdetails">

                <table>
                    <form:hidden path="Emp_Email"/>
                    <form:hidden path="task_id" />
                    <tr>
 
                     <div align="center">
                    <h1 style="color:orangered">Task Edit</h1>
                   
                         <td style="color: black">Task Type:</td>
                        <td><form:input path="task_Type" readonly="true"/></td>
                        </tr>
                        <tr>
                            <td style="color: black">Task Name:</td>
                            <td><form:input name="task_Name" path="task_Name" type="text"
                                        id="tn" readonly="true"/></td>
                        </tr>

                        <tr>
                            <td>Task Start Date:</td>
                            <td><form:input path="tStart_Time" readonly="true"/></td>
                        </tr>

                        <tr>
                            <td>Task End Date:</td>
                            <td><form:input path="tEnd_Time" readonly="true"/></td>
                        </tr>
                        <tr>

 
                        <td>Status:</td>
                        <%-- 	<td><form:input path="status" /></td> --%>
                        <td><form:select path="status" name="taskStatus">
                        
                                     <form:options items="${taskStatus}" />
                                </form:select>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2" align="center"><input type="submit"
                                                                  value="Save"></td>
                        </tr>

                </table>
            </form:form>
        </div>

    </body>
</html>
