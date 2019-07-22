<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <s:url var ="url_jqlib" value="/resources/JS/jquery-3.3.1.min.js"/>
        <script src="${url_jqlib}"></script>
        <script>
            $(document).ready(function () {
//            alert("This is test");
                $("#id_getTime").click(function () {
//                alert("Hi");
                    $.ajax({
                        url: "Ajax",
                        success: function (data) {
                            $("#id_time").html(data);
                        }
                    });
                });
            });
        </script>
        <title>JSP Page</title> 
    </head>
    <body>
        <h1>Hello World!</h1>
        <button id="id_getTime">Get Server Time</button>
        <p id="id_time"></p>
    </body>
</html>
