<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
                        alert("Enter valid Aadhar Number");
                        aadhar.focus();
                        aadhar.value = '';
                        return false;
                    }
                }
            }
        </script>

        <script>
            var myInput = document.getElementById("psw");
            var letter = document.getElementById("letter");
            var capital = document.getElementById("capital");
            var number = document.getElementById("number");
            var length = document.getElementById("length");

            //When the user clicks on the password field, show the message box
            myInput.onfocus = function () {
                document.getElementById("message").style.display = "block";
            }

            // When the user clicks outside of the password field, hide the message box
            myInput.onblur = function () {
                document.getElementById("message").style.display = "none";
            }

            //When the user starts to type something inside the password field
            myInput.onkeyup = function () {
                // Validate lowercase letters
                var lowerCaseLetters = /[a-z]/g;
                if (myInput.value.match(lowerCaseLetters)) {
                    letter.classList.remove("invalid");
                    letter.classList.add("valid");
                } else {
                    letter.classList.remove("valid");
                    letter.classList.add("invalid");
                }
                //Validate capital letters
                var upperCaseLetters = /[A-Z]/g;
                if (myInput.value.match(upperCaseLetters)) {
                    capital.classList.remove("invalid");
                    capital.classList.add("valid");
                } else {
                    capital.classList.remove("valid");
                    capital.classList.add("invalid");
                }

                // Validate numbers
                var numbers = /[0-9]/g;
                if (myInput.value.match(numbers)) {
                    number.classList.remove("invalid");
                    number.classList.add("valid");
                } else {
                    number.classList.remove("valid");
                    number.classList.add("invalid");
                }

                //Validate length
                if (myInput.value.length >= 8) {
                    length.classList.remove("invalid");
                    length.classList.add("valid");
                } else {
                    length.classList.remove("valid");
                    length.classList.add("invalid");
                }
            }
        </script>

    </head>
    <body
        background="<%=request.getContextPath()%>/resources/images/bg2.jpg">
        <%@include file="header.jsp"%>

        <div align="center">
            <h1 style="color: orangered">Edit Employee</h1>

            <form:form action="editEmployeeAjax" method="post"
                       modelAttribute="employee" commandName="employee">
                <form:errors path="*" cssClass="errorblock" element="div" />
                <table>
                    <form:hidden path="id" />

                    <tr>
                        <td style="color: yellow">Name:</td>
                        <td><form:input path="name" required="required" /></td>
                        <td><form:errors path="email" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td style="color: yellow">Email:</td>
                        <td><form:input path="email" type="email" required="required" /></td>
                    </tr>
                    <tr>
                        <td style="color: yellow">Address:</font></td>
                        <td><form:input path="address" /></td>
                    </tr>
                    <tr>

                        <td style="color: yellow">Telephone: </font></td>
                        <td><form:input path="telephone" pattern="[1-9]{1}[0-9]{9}"
                                    title="Enter valid 10 digit number" /></td>
                    </tr>

                    <tr>
                        <td style="color: white">Password:</td>
                        <td><form:input path="password" type="password" id="psw"
                                    name="psw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                    title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
                                    required="required" /></td>
                    </tr>
                    <tr>
                        <td style="color: white">Aadhar No.:</td>
                        <td><form:input path="aadhar" required="required"
                                    id="txtAadhar" onblur="AadharValidate(this);" /></td>
                        <td><form:errors path="aadhar" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td style="color: white">Pan No.:</td>
                        <td><form:input path="pan" required="required" id="pan"
                                    name="organisation_pan" onblur='ValidatePAN(this)' /></td>
                        <td><form:errors path="pan" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td style="color: white">User Type:</td>
                        <td><form:select path="category" name="userTypes"
                                     readonly="true">

                                <form:options items="${userTypes}" />

                            </form:select></td>
                    </tr>

                    <tr>
                        <td style="color: white">ManagerId</td>
                        <td><form:input path="managerId" /></td>
                    </tr>
                    <tr>
                        <td style="color: white">Status</td>
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
                        <td colspan="2" align="center"><input type="submit"
                                                              value="Save" name="action2"></td>
                    </tr>
                </table>

                <tr>
                <input type="hidden" value="${page}" name="page">
                </tr>
            </form:form>
        </div>


    </body>
</html>