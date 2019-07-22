<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>   


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
        <style type="text/css">
        #cc{
        background-color:white;
        }
        </style>
    </head>
    <body background="<%=request.getContextPath()%>/resources/images/bg2.jpg">
     <%@include file="header.jsp" %>
        <div align="center">
        <form commandName="employee">
         <h2 style="color:blue"> Welcome ${employee.name} </h2>
         <h2 style="color:green;">You have successfully logged in!!!</h2>
        </form>
            <form action="downloadProjectReport" method="post">
                <input type="submit" value="Download Project Report" > 
            </form></div><br/>
        <div align="center">
            <table border="1">
                <th>Project Name</th>
                <th>Project Start Date</th>
                <th>Project End Date</th>
                <th>Expected Duration</th>
                <th>Completion Duration</th>
                <th>Deviation Duration</th>
                <th>Project Status</th>
                   <c:forEach var="pd" items="${listProject}">
                 
                    <tr>
                        <td><a href="displayProjectDetails?project_id=${pd.project_id}">${pd.project_Name}</td>
                        
                        <div id="cc">
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${pd.pstart_Date}" var="p"/> 
                         ${p}
                        </td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${pd.pEnd_Date}" var="p1"/> 
                         ${p1}
                        </td>
                      
                        <td>
                      <%--  <c:set value="${pd.pstart_Date.time/(1000*60*60*24)}" var="datediff"/>
                         <c:set value="${pd.pEnd_Date.time/(1000*60*60*24)}" var="datediff1" />
                      
                          <c:set value="${ datediff1-datediff}" var="datediff2"/>
                          <fmt:parseNumber var="j" integerOnly="true" type="number" value="${datediff2}" /> --%>  
                    
                  <c:set var="totaldate" value="0"/>     
                  <c:forEach var="tk" items="${ tasklist}">
                  <c:if test="${(pd.project_id eq tk.projectId)}">
                    <c:set value="${tk.tStart_Time.time/(1000*60*60*24) }" var="tasksd"/>
                     <c:set value="${tk.tEnd_Time.time/(1000*60*60*24) }" var="tasked"/>
                     <c:set value="${tasked-tasksd }" var="totald"/>
                  <c:set var="totaldate" value="${ totaldate+totald}"/>
                  
                  <c:forEach var="hd" items="${holiday }" > 
                        <c:set value="${hd.hDays.time/(1000*60*60*24)}" var="hd1"/>
                       <c:if test="${(hd1 gt tasksd)and(hd1 lt tasked) }">
                        <c:set var="totaldate" value="${totaldate-1 }"/>
                       
                       </c:if>
                        </c:forEach>
                  
                  </c:if>
                  </c:forEach>     
                       <%-- <fmt:parseNumber var="j" integerOnly="true" type="number" value="${totaldate}" />  
                       
                       <c:forEach var="hd" items="${holiday }" > 
                        <c:set value="${hd.hDays.time/(1000*60*60*24)}" var="hd1"/>
                       <c:if test="${(hd1 gt datediff)and(hd1 lt datediff1) }">
                        <c:set var="j" value="${j-1 }"/>
                       
                       </c:if>
                        </c:forEach> --%>
                      <fmt:parseNumber var="j" integerOnly="true" type="number" value="${totaldate}" />
                        ${j} Days
                       </td>
                        <td>
                      <c:set var="date14" value="0"/>
                 <c:set var="k" value="0"/>
                 <c:set var="hday" value="0"/>
                 <c:set var="status1" value="Completed"/>
                      <c:forEach var="tl" items="${tasklist}">
                      
                       <c:if test="${(pd.project_id eq tl.projectId)}">
                       <c:if test="${not empty tl.tStart_Time  }">
                       <c:choose>
                       <c:when test="${  empty tl.tSub_Date  }">
                       <c:set var="Datee1" value="<%=new java.util.Date()%>" />  
                        <c:set value="${Datee1.time/(1000*60*60*24)}" var="d"/>
                          <c:set value=" ${tl.tEnd_Time.time/(1000*60*60*24) } "  var="d1"/>
                          <c:set value="${d-d1 }" var="d22"/>
                          
                          <c:set value="${tl.tStart_Time.time/(1000*60*60*24) }" var="tss"/>
                           <c:set value="${tl.tEnd_Time.time/(1000*60*60*24) }" var="tse"/>
                           <c:set value="${tse-tss }" var="tsst"/>
                        
                           
                           <c:set var="d2" value="${tsst+d22 }"/>
                          <fmt:parseNumber var="d3" integerOnly="true" type="number" value="${d2}" />
                           <c:set var="date14" value="${d3 }"/>
                       </c:when>
                       <c:otherwise>
                         <fmt:parseDate pattern="yyyy-MM-dd" value="${tl.tSub_Date }" var="p"/>
                       <c:set value="${p.time/(1000*60*60*24)}" var="p1"/>
                        <c:set value=" ${tl.tEnd_Time.time/(1000*60*60*24) } "  var="p2"/>
                        <c:set value="${p1-p2 }" var="p33"/>
                        
                         <c:set value="${tl.tStart_Time.time/(1000*60*60*24) }" var="tss"/>
                           <c:set value="${tl.tEnd_Time.time/(1000*60*60*24) }" var="tse"/>
                           <c:set value="${tse-tss }" var="tsst"/>
                           
                            <c:set var="p3" value="${tsst+p33 }"/>
                        
                        
                          <fmt:parseNumber var="p4" integerOnly="true" type="number" value="${p3}" />
                       <c:set var="date14" value="${p4}"/> 
                       </c:otherwise>
                       </c:choose>
                       <c:set var="k" value="${date14+k }"/>
                       
                        <c:forEach var="hd2" items="${holiday }" > 
                        <c:set value="${hd2.hDays.time/(1000*60*60*24)}" var="hd3"/>
                       <c:if test="${(hd3 gt tss)and(hd3 lt tse) }">
                        <c:set var="hday" value="${hday+1 }"/>
                         </c:if>
                        </c:forEach>
                       
                     </c:if>
                     <c:if test="${( empty tl.tSub_Date) or (tl.taskStatus ne 'complete')  }">
                       <c:set var="status1" value="Work In Progress"/>
                      </c:if>

                    </c:if>
                       </c:forEach>
                       
                      
                      <c:set var="k" value="${k-hday }"/>
                       

                    <%--   
                     </c:if> --%>
                   <%--     </c:forEach>   --%>

                        ${k} Days
                  </td><td>
                     <c:set var="deviation" value="${k-j }"/>
                      ${deviation } Days
                        </td>
                        <td>${status1}</td></div>
                    </tr>
                </c:forEach> 
            </table>
        </div>

    </body>
</html>
