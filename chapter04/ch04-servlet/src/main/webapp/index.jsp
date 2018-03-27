<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Servlet</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/InitConfigServlet">InitConfigServlet</a>
        <br />
        <br />
        <form action="${pageContext.request.contextPath}/AsyncServlet" method="GET">
            <h2>AsyncServlet</h2>
            Milliseconds
            <br />
            <input type="number" id="timestamp" name="timestamp" style="width: 200px" value="5000"/>
            <button type="submit">Submit</button>
        </form>

    </body>
</html>
