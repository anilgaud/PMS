<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="<%=request.getContextPath()%>/resources/images/bg2.jpg">
 <jsp:include page="header.jsp"/>

        <div align="center">
            <h1 style="color: orangered">Edit Project</h1>
        <h3> <p style="color: orangered">${msg}</p></h3>
            <form:form action="editproject1" method="post"
                       modelAttribute="pd" commandName="pd">
                <table>
                
                    <form:hidden path="project_id" />
                    <form:hidden path="manageremail" />
                    <tr>
                        <td>Project Name:</td>
                        <td><form:input type="text" path="project_Name" name="project_Name" required="true" value="${pd.project_Name}"/></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><form:textarea path="project_desc" rows="5" cols="5" name="project_Name"/></td>
                    </tr>
                    <tr>
                        <td>Project Start Date:</td>
                        <td><form:input path="pstart_Date" type="date" name="pstart_Date" required="true"/></td>
                    </tr>

                    <tr>
                        <td>Project End Date:</td>
                        <td><form:input path="pEnd_Date" type="date" id="endDate"
                                    name="endDate" required="true"/></td>
                    </tr> 

                    <tr>

                        <td><input type="submit" value="Edit Project"></td>

                    </tr>
                </table>
                </form:form>
                
                <form:form action="back" method="Post">
                
                	<input type="submit" value="Back">
                
                
                </form:form>
</body>
</html>