<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<meta charset="ISO-8859-1">

<title>Create Task</title>



<script>
            history.pushState(null, null, location.href);
            window.onpopstate = function () {
                history.go(1);
            };

        </script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
    	function f1(pid) {
    		var returned = true;
    		
    		var r = confirm("Are you sure want to delete");
    		if (r == true) {
    			var url = "taskAjax?pid=" + pid;
    			

    			$.ajax({
    				url : url,
    				async : false,
    				success : function(result) {

    					console.log("SUCCESS: ", result);
    				
    					if (result == 0) {

    						alert("You can't delete Task!!");
    						returned = false;
    						return false;
    						f3(returned)
    						
    					} 
    					else
        					{
        					alert('Task deleted successfully')
        					console.log(result);
        					listTask();
        					 
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
<script>
list = "";
      $(document).ready(function(){

    	     $('#ct').click(function(){


    	    	 var str = $("#tk").serialize(); 
    	    	 $.ajax({
    	 			type : "post",
    	 			data : str,
    	 			url : "createTask1",
    	 			
    	 			success : function(d) {
    	 				alert('Task created successfully');
    	 				 listTask();
    	 				refresh();
    	 		 	}
    	 		});

        	     });

          });
    listTask = function(){
		$.ajax({
				url:'listTaskAjax',
				type:'GET',
			    success: function (response) {
			    	list = response.list;
		          
		            $('.tr').remove();
		            for (i = 0; i < response.list.length; i++) {
		                 $("#tbl").append("<tr class='tr'> <td> " + response.list[i].task_Type + " </td><td>"+response.list[i].task_Name+"</td><td><a href='asssign?task_id="+response.list[i].task_id+"&project_id="+response.list[i].projectId+"'>"+response.list[i].status+"</a></td><td><a href='#' onclick=f1("+response.list[i].task_id+")>Delete</a></td></tr>");

		            }
		        }	
			})
	   };
	   refresh = function () {
	  	    $("#tname").val("");  
		};

        </script>
</head>
<body
	background="<%=request.getContextPath()%>/resources/images/bg2.jpg" onload="listTask()">
	<%@include file="header.jsp"%>
	<div class="content">

		<h1 style="color: orangered" align="center">Create Task</h1>
		<form:form action="createtask" method="post"
			modelAttribute="taskdetails" id="tk">
			<table align="center">

				<form:hidden path="projectId" />
				<form:hidden path="managerId" />
				<form:hidden path="Emp_Email" />
				<tr>
					<td style="color: black">Task Name:</td>
					<td><form:input name="task_Name" path="task_Name" type="text"
							id="tname" /></td>
				</tr>
				<tr>
					<td style="color: black">Task Types:</td>
					<td><form:select path="task_Type" name="task_Type">
							<form:options items="${task_Type}" />
						</form:select></td>
				<tr>

					<input type="hidden" name="em" value="${em }" />
					<td colspan="2" align="right"><input type="button"
						value="CREATE" id="ct"></td>

				</tr>
				</form:form>
				</div>
				<div align="right">
					<form:form action="backtoproject">

						<table>
							<tr>
								<td>
									<%--  	<form:hidden path="email" name="email"/>
                                    
                                    <input type="submit" value="Back">
                            --%> <%--  <a href="backtoproject?email=${e.email}"><font style="color:Dark blue" size="5">Back</font></a>  --%>
									<a href="backtoproject?email=${em}"><font
										style="color: Dark blue" size="5">Back</font></a>



								</td>
						</table>
					 

					</form:form>


					<table border="1" align="center" id="tbl">

						<th style="color: red">Task Type</th>
						<th style="color: red">Task Name</th>
						<th style="color: red">Status</th>
						<th style="color: red">Delete</th>
 

					</table>
				</div>
</body>
</html>


