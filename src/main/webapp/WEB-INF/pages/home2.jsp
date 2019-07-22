<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%-- <%@ page import="com.kgate.service.SkillService;" %> --%>

<html>
    <head>
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Management Screen</title>
        <style type="text/css">
            #bt {
                /*background-image: url('/WEB-INF/images/Search-icon.png');*/
                background-repeat: no-repeat;
                background-position: left;
                background-size: 40px;
            }
        </style>
        <spring:url value="/resources/JS/emp.js" var="c"></spring:url>
        <script src="${c}"></script>
    </head>


    <body
        background="<%=request.getContextPath()%>/resources/images/bg2.jpg">
        <%@include file="header.jsp"%>
        <div align="center">
 
            <center><a href="/PMS-1.2/downloadPDF"><font style="color:blue" size="4">Download Employee PDF</font></a><br></center>

            <center><a href="/PMS-1.2/downloadExcel"><font style="color:blue" size="4">Download Employee Excel</font></a><br></center>

            <h1 style="color: maroon">Employee List</h1>

            <form action="<s:url value="/search_employeelist"/>">

                <input type="text" name="freeText"
                       placeholder="Enter Name or Email or Address To Search"
                       value="${param.freeText}" />

                <button>
                    <div id="bt">Find</div>
                </button>
            </form>
            <br>
            <form action="<s:url value="/search_employeelist_skill1"/>"
                  onsubmit="return chk()" name="skillform">
                <td><input type="text" name="skillSearch"
                           placeholder="Enter Skill To Search" value="${param.skillSearch}" />
                    <button>Find</button>
            </form>

            <br>
            <p style="color: red">${error}</p>
            <p style="color: Dark blue">${message}</p>
            <table border="1" id="tbl">

                <th style="color: red">Name</th>
                <th style="color: red">Email</th>
                <th style="color: red">Address</th>
                <th style="color: red">Telephone</th>
                <th style="color: red">User Type</th>
                <th style="color: red">Action</th>
 
            </table><br>
            <div align="center">Pages: <p id="n"></p></div>
            <tr>

            </tr>
            <h4>
                <font style="color: darkorange"> New Employee Register</font> <a
                    href="newEmployee" style="color: red;">Here</a>
            </h4>

            <form:form action="backtosuccess" method="post">

                <input type="submit" value="Back">
            </form:form>
        </div>

    </div>

</div>

</body>

</html>
