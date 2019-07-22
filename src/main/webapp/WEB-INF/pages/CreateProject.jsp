<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>

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
        
        <script>
    	function f1(pid) {
    		var returned = true;
    		
    		var r = confirm("Are you sure want to delete");
    		if (r == true) {
    			var url = "getProjecttask?pid=" + pid;
    			

    			$.ajax({
    				url : url,
    				async : false,
    				success : function(result) {

    					console.log("SUCCESS: ", result);
    				
    					if (result > 0) {

    						alert("You can't delete Project");
    						returned = false;
    						return false;
    						f3(returned)
    						
    					} 
    					else
        					{
        					alert("Project deleted successfully");

        					}
    					
    				}
    			});

    		} else 
        	{
    			return false;
    		}

    		if(returned)
        		{
    			
        		}
    		else
        		{
        	
        		return false;
        		}
    	
    	}
    	function f3(flag)
		{
			if(flag)
    		{
			
    		}
			else
				{
				console.log("anil flase");
	    		alert('flag false');
	    		return false;
	    	
				}
		
    		}

        </script>

    </head>
    <body
        background="<%=request.getContextPath()%>/resources/images/bg2.jpg">
 <jsp:include page="header.jsp"/>

        <div align="center">
            <h1 style="color: orangered">Create Project</h1>
        <h3> <p style="color: orangered">${msg}</p></h3>
            <form:form action="cproject" method="post"
                       modelAttribute="pd" commandName="pd">
                <table>
                
                    <form:hidden path="project_id" />
                    <form:hidden path="manageremail" />
                    <tr>
                        <td>Project Name:</td>
                        <td><form:input type="text" path="project_Name" name="project_Name" required="true"/></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><form:textarea path="project_desc" rows="5" cols="5" name="project_Name"/></td>
                    </tr>
                     <tr>
                        <td>Project Start Date:</td>
                      
                         <c:set var="d1" value="<%=new java.util.Date()%>" />  
                          <fmt:formatDate pattern="yyyy-MM-dd" value="${d1 }" var="p"/>
                        <td><form:input path="pstart_Date" type="date" min="${p }" max="2039-12-12" name="pstart_Date" required="true"/></td>
                    </tr>

                    <tr>
                        <td>Project End Date:</td>
                         <c:set var="d1" value="<%=new java.util.Date()%>" />  
                          <fmt:formatDate pattern="yyyy-MM-dd" value="${d1 }" var="p"/>
                        <td><form:input path="pEnd_Date" type="date" id="endDate"
                                    name="endDate" min="${p }"  max="2039-12-12" required="true"/></td>
                    </tr> 


                    <tr>

                        <td><input type="submit" value="Create Project"></td>

                    </tr>
                </table>

                <table border="1" align="left">

                    <th style="color: red">Project Name</th>
                     <th style="color: red">Edit Action</th>
                      <th style="color: red">Delete Action</th>

                    <c:forEach var="pd" items="${listProject}">
                        <tr>

                            <td><a href="showtask?project_id=${pd.project_id}&mgrid=${mid}" style="color: Dark blue">${pd.project_Name}</a></td>
                            <td><a href="editproject?project_id=${pd.project_id}" style="color: Dark blue">Edit</a>
                            <%-- <td><a href="deleteproject?project_id=${pd.project_id}" style="color: Dark blue" onclick="return confirm('Are you sure you want to delete this Project?');">delete</a> --%>
                 <td><t><a href="deleteproject?project_id=${pd.project_id}" style="color: Dark blue" onclick="return f1('${pd.project_id}')">delete</a>  </t>
                        </tr>


                    </c:forEach>
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
