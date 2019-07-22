<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New/Edit Contact</title>
        <style>
            .error {
                color: red;
                font-weight: bold;
            }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <spring:url value="/resources/JS/cproject.js" var="cp"/>
        <script src="${cp}"></script>
    </head>
    <body
        background="<%=request.getContextPath()%>/resources/images/bg2.jpg" onload="list3()">
        <jsp:include page="header.jsp"/>
        <p>Hi</p>
        <div align="center">
            <h1 style="color: orangered">Create Project</h1>
            <h3> <p style="color: orangered">${msg}</p></h3>
                <form:form action="cproject" method="post"
                           modelAttribute="pd" commandName="pd" id="form1">
                <table>

                    <form:hidden path="project_id" />
                    <tr>
                        <td>Project Name:</td>
                        <td><form:input type="text" path="project_Name" id="project_Name" name="project_Name" required="true"/></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><form:textarea path="project_desc" rows="5" id="project_desc" cols="5" name="project_Name"/></td>
                    </tr>
                    <tr>
                        <td>Project Start Date:</td>

                        <c:set var="d1" value="<%=new java.util.Date()%>" />  
                        <fmt:formatDate pattern="yyyy-MM-dd" value="${d1 }" var="p"/>
                        <td><form:input path="pstart_Date" type="date" min="${p }" id="pstart_Date" max="2039-12-12" name="pstart_Date" required="true"/></td>
                    </tr>

                    <tr>
                        <td>Project End Date:</td>
                        <c:set var="d1" value="<%=new java.util.Date()%>" />  
                        <fmt:formatDate pattern="yyyy-MM-dd" value="${d1 }" var="p"/>
                        <td><form:input path="pEnd_Date" type="date" id="pEnd_Date"
                                    name="endDate" min="${p }"  max="2039-12-12" required="true"/></td>
                    </tr> 


                    <tr>

                        <td><input type="button" id="btn" value="Create Project"></td>

                    </tr>
                </table>

                <table border="1" id="table1" align="left">
                    <th style="color: red">Project Name</th>
                    <th style="color: red">Edit Action</th>
                    <th style="color: red">Delete Action</th>
                </table>
            </form:form>
        </div>
        <br>

        <div align="right">
            <form:form action="managerpage"  modelAttribute="employee">

                <table>
                    <tr>
                        <td>
                            <a href="managerpage?email=${employee.email}">Employee List</a>
                </table>
            </form:form>
        </div>

        <div align="right">
            <form:form action="backtomanagerDashboard" method="post" commandName="employee">

                <table>
                    <tr>
                        <td>
                            <input type="submit" value="Back">

                        </td>
                    </tr>


                </table>

            </form:form>
        </div>


    </body>

</html>
