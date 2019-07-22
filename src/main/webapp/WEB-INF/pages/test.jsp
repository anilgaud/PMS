<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Skill Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <spring:url value="/resources/JS/jquery.js" var="a"/>
        <spring:url value="/resources/css/mycss.css" var="cs"/>
        <script src="${a}"></script>
        <link href="${cs}" rel="stylesheet"/>
    </head>

    <body
        background="<%=request.getContextPath()%>/resources/images/bg2.jpg"  onload="load()">
    <tr> <form:form action="backtosuccess" method="post"><input type="submit" value="Back"></form:form> </tr>

        <div align="center">
            <h1 style="color:red">Skills Tracker: Add Skill Page</h1>
            <div style="color: red">${error}</div> 

            <form:form id="skill_Form"  method="post" modelAttribute="skill"
                       commandName="skill">
                <table>

                    <form:hidden path="skill_Id" id="skill_Id"/>
                    <tr>
                        <td style="color: Dark blue">Name:</td>
                        <td><form:input path="skill_name" name="skill_name"
                                    id="skill_name"  required="true"/> </td>
                     <br>
                    <td colspan="2" align="center"><input type="submit"
                                                          value="Add" ></td>
                    </tr>
                </table>
            </form:form>
            <input id="myInput" type="text" placeholder="Search Skill Here.."  /><br></br> 
            <table id="table1" border=1>
                <th> Name </th><th> Edit </th> <th> Delete</th> 

            </table>
            <p></p> 
        </div>
    </body>
</html>
