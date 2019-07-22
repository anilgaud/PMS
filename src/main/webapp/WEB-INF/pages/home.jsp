<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <%@ page import="com.kgate.service.SkillService;" %> --%>

<html>
    <head>
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Management Screen</title>
        <style type="text/css">
            #bt {
                background-image: url('/WEB-INF/images/Search-icon.png');
                background-repeat: no-repeat;
                background-position: left;
                background-size: 40px;
            }
        </style>
        <script>
            function chk() {
                var x = document.forms["skillform"]["skillSearch"].value;
                if (x == "" || x.length < 3) {
                    alert("Skill is empty or Atleast require 3 character to find the result ");
                    return false;
                }
            }

            $(document).ready(function () {
                $('cc').click(function () {

                    console.log("Hello world! anil");
                    /* alert("Skill is empty or Atleast require 3 character to find the result ");  */
                    $.ajax({
                        url: 'success1',
                        success: function (result) {
                            console.log("SUCCESS: ", result);
                            console.log("Hello world! anil");

                        }
                    });
                });
            });

            function f1(mail) {
                var returned = true;

                var r = confirm("Are you sure want to delete");
                if (r == true) {
                    var url = "success1?mail=" + mail;


                    $.ajax({
                        url: url,
                        async: false,
                        success: function (result) {

                            console.log("SUCCESS: ", result);

                            if (result > 0) {

                                alert("You can't delete employee");
                                returned = false;
                                return false;
                                f3(returned)

                            } else
                            {
                                alert("Employee deleted successfully");

                            }

                        }
                    });

                } else
                {
                    return false;
                }

                if (returned)
                {

                } else
                {

                    return false;
                }

            }
            function f3(flag)
            {
                if (flag)
                {

                } else
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
            <table border="1">

                <th style="color: red">Name</th>
                <th style="color: red">Email</th>
                <th style="color: red">Address</th>
                <th style="color: red">Telephone</th>
                <th style="color: red">User Type</th>
                <th style="color: red">Action</th>


                <c:forEach var="employee" items="${listEmployee}">
                    <tr style="color: Dark blue">

                        <td>${employee.name}</td>
                        <td>${employee.email}</td>
                        <td>${employee.address}</td>
                        <td>${employee.telephone}</td>
                        <td>${employee.category}</td>
                        <td><a href="editEmployee?id=${employee.id}&page=${page}"
                               style="color: blue" onclick="return confirm('Are you sure you want to edit this employee?');">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; 
                            <a
                                href="deleteEmployee?id=${employee.id}&email=${employee.email}&page=${page}"
                                style="color: maroon" onclick=" return f1('${employee.email}')">Delete</a></td>

                    </tr>
                </c:forEach>
            </table>

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

    <div class="pagination">
        <ul style="list-style-type:none">

            <li>Page
                <!--   <a href="employeelist?page=0">0</a> -->
                <c:forEach begin="${startpage}" end="${endpage}" var="p">
                    <a href="<c:url value="employeelist" >
                           <c:param name="page" value="${p}"/>${p}</c:url>">${p}</a>
                </c:forEach>
            </li>

        </ul>






    </div>





</div>

<!--   <div class="pagination">
 <ul>
     
     
     <li class="active"><a href="employeelist?page=1">1</a></li>
     <li class="active"><a href="">2</a></li>
     <li class="active"><a href="#">3</a></li>
     <li class="active"><a href="#">4</a></li>
     <li class="active"><a href="#">5</a></li>
     <li class="active"><a href="#">Next</a></li>
     <li class="active"><a href="#">Last</a></li>
 </ul>
</div> -->




</body>

</html>
