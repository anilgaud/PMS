<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

<spring:url value="/resources/JS/jquery1.js" var="as"></spring:url>
<script src="${as}"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	function ValidatePAN() {
		var pan_no = document.getElementById("pan");
		if (pan_no.value != "") {
			PanNo = pan_no.value;
			var panPattern = /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})$/;
			if (PanNo.search(panPattern) == -1) {
				alert("Invalid Pan No");
				pan_no.focus();
				pan_no.value = '';
				return false;
			}
		}
	}
</script>

<script type="text/javascript">
	function ValidatePAN() {
		var pan_no = document.getElementById("pan");
		if (pan_no.value != "") {
			PanNo = pan_no.value;
			var panPattern = /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})$/;
			if (PanNo.search(panPattern) == -1) {
				alert("Invalid Pan No");
				pan_no.focus();
				pan_no.value = '';
				return false;
			}

		}
	}
</script>

<script type="text/javascript">
	function AadharValidate() {

		var aadhar = document.getElementById("txtAadhar");
		var adharcardTwelveDigit = /^\d{12}$/;
		var adharSixteenDigit = /^\d{16}$/;
		if (aadhar.value != "") {
			aadharNo = aadhar.value;
			if (aadharNo.match(adharcardTwelveDigit)) {
				return true;
			} else if (aadharNo.match(adharSixteenDigit)) {
				return true;
			} else {
				alert("Enter Valid Aadhar Number");
				aadhar.focus();
				aadhar.value = '';
				return false;
			}
		}
	}
</script>

<style>
/* Style the container for inputs */
.container {
	background-color: #f1f1f1;
	padding: 20px;
}

/* The message box is shown when the user clicks on the password field */
#message {
	display: none;
	background: #f1f1f1;
	color: #000;
	position: relative;
	padding: 20px;
	margin-top: 10px;
}

#message p {
	padding: 10px 35px;
	font-size: 18px;
}
/* Add a green text color and a checkmark when the requirements are right */
.valid {
	color: green;
}

.valid:before {
	position: relative;
	left: -35px;
	content: "&#10004;";
}

/* Add a red text color and an "x" icon when the requirements are wrong */
.invalid {
	color: red;
}

.invalid:before {
	position: relative;
	left: -35px;
	content: "&#10006;";
}
</style>
<script>
$(document).ready(function() {
	
	$('#f').click(function() {
		var str = $("#eedit").serialize(); 
		
		
		$.ajax({
			type : "post",
			data : str,
			url : "byEmployeeEdit1",
			
			success : function(d) {
				alert('Details save successfully');
				
			}
		});

	});

});

</script>
</head>
<body
	background="<%=request.getContextPath()%>/resources/images/bg2.jpg">
	<%@include file="header.jsp"%>

	<div align="center">
		<form commandName="employee">
			<h2 style="color: orangered">Welcome ${employee.name}</h2>
			<h2 style="color: green">you have successfully Login</h2>


		</form>
		<h1 style="color: orangered">Edit Employee</h1>
		<h3>
			<p style="color: green">${msg}</p>
			<p id="msg1">
			<p>
		</h3>
		<form:form method="post" modelAttribute="employee" 
			commandName="employee" id="eedit">
			<form:errors path="*" cssClass="errorblock" element="div" />
			<table>
				<form:hidden path="id" />
				<form:hidden path="category" />
				<form:hidden path="otp" />
				<tr>
					<td style="color: yellow">Name:</td>
					<td><form:input path="name" required="required"
							pattern="^[A-Za-z\\s]*$" /></td>
					<td><form:errors path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td style="color: yellow">Email:</td>
					<td><form:input path="email" type="email" required="required"
							readonly="true" /></td>
				</tr>
				<tr>
					<td style="color: yellow">Address:</font></td>
					<td><form:input path="address" readonly="true" /></td>
				</tr>
				<tr>

					<td style="color: yellow">Telephone: </font></td>

					<td><form:input path="telephone" pattern="[1-9]{1}[0-9]{9}"
							title="Enter valid 10 digit number" /></td>

				</tr>

				<tr>
					<td style="color: yellow">Password:</td>


					<td><form:input path="password" type="password" id="psw"
							name="psw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
							required="required" /></td>


				</tr>

				<tr>
					<td style="color: yellow">Aadhar No.:</td>
					<td><form:input path="aadhar" required="required"
							id="txtAadhar" onblur="AadharValidate(this);" /></td>
					<td><form:errors path="aadhar" cssClass="error" /></td>
				</tr>
				<tr>
					<td style="color: yellow">Pan No.:</td>
					<td><form:input path="pan" id="pan" name="organisation_pan"
							onblur='ValidatePAN(this)' required="required" /></td>
					<td><form:errors path="pan" cssClass="error" /></td>
				</tr>

				<tr>
					<td style="color: yellow">ManagerId</td>
					<td><form:input path="managerId" readonly="true" /></td>
				</tr>
				<tr>
					<td style="color: yellow">Status</td>
					<td><form:input path="status" readonly="true" /></td>
				</tr>

				<tr>
					<td style="color: yellow">Skills: </font></td>
					<td><form:select path="skills" required="true">

							<form:options items="${listSkill}" />
						</form:select>
					<td>
				</tr>

				<tr>
					<!-- <td><input type="submit" value="Send OTP" name="action1" /></td>-->
					<td id="f" colspan="2" align="center"><input type="button"
						value="Save" name="action2"></td>

				</tr>
			</table>
		</form:form>
		<form:form action="etasklist" method="post" commandName="employee">

			<form:hidden path="email" name="email" />
			<form:hidden path="status" name="status" />
			<input type="submit" Value="Task Details">
			<div style="color: red">${error}</div>
			<tr>


			</tr>
		</form:form>

	</div>

</body>



</html>
