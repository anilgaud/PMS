<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New/Edit Contact</title>
        <style>
            .error { 
                color: red; font-weight: bold; 
            }
        </style>

    </head>
    <body background="<%=request.getContextPath()%>/resources/images/bg2.jpg">
        <%@include file="header.jsp" %>


        <div align="center">
            <h1 style="color:orangered">Allocate Task</h1>


           <c:forEach var="pd1" items="${pd }">


            <form:form action="taskAllocated" method="post" modelAttribute="td" commandName="td">
                <form:errors path = "*" cssClass = "errorblock" element = "div" />
                <table>
                    <!-- <table border="1" align="center" width="800" height="200"> -->
                    <form:hidden path="projectId" />
                    <form:hidden path="managerId" />
                    <form:hidden path="task_id" />


                    <tr>
                        <td style="color:white">Task Type: </td>
                        <td><form:input path="task_Type"  /></td>
                        <td><form:errors path="task_Type" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td style="color: black">Task Name:</td>
                        <td><form:input name="task_Name" path="task_Name" type="text" id="tn" /></td>
                    </tr>

                    <tr>
                         
                        <td>Task Start Date:</td>
                        <td><form:input path="tStart_Time" type="date" min="${pd1.pstart_Date }" max="${pd1.pEnd_Date }"  required="true"/></td>
                        
                    </tr>

                    <tr>
                        <td>Task End Date:</td>
                        <td><form:input path="tEnd_Time" type="date" min="${pd1.pstart_Date }" max="${pd1.pEnd_Date }" required="true"/></td>
                    </tr>

                    <tr>
                        <td style="color:white"> Assign: </font></td>
                        <td>
                            <form:select path="emp_name" required ="true">

                                <form:options items="${empnameList}" />
                            </form:select>

                        <td>

                    </tr>  
                    <input type="hidden" name="em" value="${em }"/>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Save" name="action2"></td>
                    </tr>

                </table>

                <form:form action="backtotask" method="post"  > 
                    <div align="left"><td>
  <!--                <td>  <input type="submit" value="Back"></form:form> </td>-->
                    </div>
            </form:form>

            </c:forEach>
      </body>

</html>

