<%!
    int pageCount = 0;

    void addCount() {
        pageCount++;
    }
%>

<% addCount();%>

<html>

    <head>
        <title>Header Example</title>
    </head>

    <body>
    <center>
        <%-- <h2>The include Directive Example</h2>
         <p>This site has been visited <%= pageCount %> times.</p>
        --%>
        <div align="right">
            <form action="logout" method="get">
                <input type="submit" value="Logout"> 
            </form></div>
    </center>
    <br/><br/>
</body>
</html>


