<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>

        <SCRIPT type="text/javascript">
            window.history.forward();
            function noBack() {
                window.history.forward();
            }
        </SCRIPT>

        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body background="<%=request.getContextPath()%>/resources/images/pineapple.jpg">

        <%@include file="header.jsp" %>
     
        <div align="center">
            <form:form action="/PMS-1.2/MngEdit" method="post"
                       modelAttribute="employee" commandName="employee">
                          <h2 style=color:blue> Welcome ${employee.name} </h2>
                        
                         <h2 style="color:green">You have successfully logged in!!!</h2>
                <form:hidden path="email" />
                <input type="submit" value="Edit Profile">
            </form:form>
            </br>
            <form:form action="/PMS-1.2/createProject2" method="post"  modelAttribute="employee">
                <form:hidden path="email" />
                <input type="submit" value="Create Project">
            </form:form>
            </br>

            <form:form action="/PMS-1.2/managerProjectView" method="post"  modelAttribute="employee">
                <form:hidden path="email" />
                <input type="submit" value="Project Status">
            </form:form>
          
        </div>

    </body>
</html>

