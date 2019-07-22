<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $("input").focus(function () {
                    $(this).css("background-color", "#cccccc");
                });
                $("input").blur(function () {
                    $(this).css("background-color", "#ffffff");
                });
            });
            $(document).ready(function () {
                $("#myInput").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    $("#myTable tr").filter(function () {
                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                    });
                });
            });
            $(document).ready(function () {
                $("a[id$='id_delete']").click(function () {
                    alert("Do you want to delete?");
                });
            });
//            $(document).ready(function () {
//                $("a[id$='id_edit']").click(function () {
//                    $("input[id$='myInput']").hide();
//                });
//            });
        </script>


        <style>
            table {
                border-collapse: collapse;
            }
            th, td {
                text-align: left;
                padding: 8px;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Add Skills</title>
    </head>
    <body
        background="<%=request.getContextPath()%>/resources/images/bg2.jpg">

    <tr> <form:form action="backtosuccess" method="post"><input type="submit" value="Back"></form:form> </tr>
    <div align="center">
        <h1 style="color:red">Skills Tracker: Add Skill Page</h1>
        <div style="color: red">${error}</div> 
    <form:form action="saveTest" method="post" modelAttribute="skill"
               commandName="skill">
        <table>

            <form:hidden path="skill_Id" />
            <tr>
                <td style="color: Dark blue">Name:</td>
                <td><form:input path="skill_name" name="skill_name"
                            id="skill_name"  required="true"/> </td>
                <!--  <input type='text' pattern='[A-Za-z\\s]*'/> -->

            <br>
            <td colspan="2" align="center"><input type="submit"
                                                  value="Add"></td>

            </tr>


        </table>

    </form:form>
    <input id="myInput" type="text" placeholder="Search Skill Here.."  /><br></br> 

    <table border="1">
        <th>Skills</th>
        <th>Action</th>
        <tbody id="myTable">
            <c:forEach var="skill" items="${listSkill}">
                <tr>

                    <td>${skill.skill_name}</td>

                    <%-- <td><a href="editTest?skill_Id=${skill.skill_Id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;  --%>

                    <td><a href="editTest?skill_Id=${skill.skill_Id}" style="color:blue" onclick="return confirm('Are you sure you want to edit this skill?');">Edit</a>    
                        &nbsp;&nbsp;&nbsp;&nbsp; 

                        <%--  <a href="deleteTest?skill_Id=${skill.skill_Id}" id="id_delete">Delete</a> --%>

                        <a href="deleteTest?skill_Id=${skill.skill_Id}" id="id_delete" style="color:maroon" onclick="return confirm('Are you sure you want to delete this skill?');">Delete</a>
                    </td>

                </tr>

            </c:forEach>
        </tbody>
    </table>

</div>

</body>

</html>