<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body background="<%=request.getContextPath()%>/resources/images/bg2.jpg">
   <%@include file="header.jsp" %>
        <div align="center">

            <form action="downloadReport" method="post">
                <input type="hidden" name="project_id" value="${Pid}">
                <input type="submit" value="Download Task Report" > 
            </form>
                </br></br>
            <table border="1">

                <th style="color:red">Employee Name</th>
                <th style="color:red">Manager Name</th>
                <th style="color:red">Task Name</th>
                <th style="color:red">Task Type</th>
                <th style="color:red">Start Date</th>
                <th style="color:red">End Date</th>
                <th style="color:red">Status</th>
                <th style="color:red">Delay</th>


                <c:set var="total"/>
                <c:forEach var="d" items="${listProject}">
                    <tr style="color:black">

                        <td>${d.emp_name}</td>
                        <td>${d.name}</td>
                        <td>${d.task_Name}</td>
                        <td>${d.task_Type}</td>
                        <td>${d.tStartDate}</td>
                        <td>${d.tEndDate}</td>
                        <td>${d.status}</td>
                        <td>
                       <c:choose>
                       <c:when test="${empty d.tsubDate }">
                       <c:set var="ed" value="<%=new java.util.Date()%>" />
                       <c:set value="${ed.time/(1000*60*60*24)}" var="dd"/>
                       <fmt:parseDate pattern="yyyy-MM-dd" value="${d.tEndDate}" var="a"/>
                       <c:set value="${a.time/(1000*60*60*24)}" var="start"/>
                       <c:set value="${dd-start }" var="t"/>
                       </c:when>
                       <c:otherwise>
                       <fmt:parseDate pattern="yyyy-MM-dd" value="${d.tsubDate}" var="d1"/>
                       <c:set value="${d1.time/(1000*60*60*24)}" var="start1"/>
                        <fmt:parseDate pattern="yyyy-MM-dd" value="${d.tEndDate}" var="a1"/>
                        <c:set value="${a1.time/(1000*60*60*24)}" var="start2"/>
                         <c:set value="${start1-start2}" var="t"/>
                       </c:otherwise>
                       </c:choose>
                        <fmt:parseDate pattern="yyyy-MM-dd" value="${d.tEndDate}" var="a11"/>
                         <c:set value="${a11.time/(1000*60*60*24)}" var="start22"/>
                         
                          <fmt:parseDate pattern="yyyy-MM-dd" value="${d.tStartDate}" var="a12"/>
                         <c:set value="${a12.time/(1000*60*60*24)}" var="start23"/>
                         
                         
                         <c:set value="${start22-start23 }" var="c"/>
                         <c:set value="${c+t }" var="t1"/>
                         
                         
                         
                       
                        <%--  <fmt:parseNumber var="days" integerOnly="true" type="number" value="${t1}"/> --%>
                         <fmt:parseNumber var="days" integerOnly="true" type="number" value="${t}"/>
                        ${days}
                           <c:set value="${total+days}" var="total"/>
                           <%--  <fmt:parseDate pattern="yyyy-mm-dd" value="${d.tEndDate}" var="a"/>
                            <c:set value="${a.time/(1000*60*60*24)}" var="start"/>
                            <fmt:parseDate pattern="yyyy-mm-dd" value="${d.tStartDate}" var="b"/>
                            <c:set value="${b.time/(1000*60*60*24)}" var="end"></c:set>
                            <c:set value="${start-end}" var="difference"></c:set>
                            <fmt:parseNumber var="days" integerOnly="true" type="number" value="${difference}"></fmt:parseNumber>
                            Delay Days ${days}
                            <c:set value="${total+days}" var="total"/> --%>
                            
                        </td>

                    </tr>
                </c:forEach>
                </table>
             <p style="margin-left: 43%">  Total Delay days::${total}</p>
            
   <form action="CeoBack" method="post">
        <input type="submit" value="Back"> 
        </form>
         
        </div>
        
    </body>
</html>

