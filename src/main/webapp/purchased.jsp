<%--
  Created by IntelliJ IDEA.
  User: shayan
  Date: 1/26/2021
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
    <h3>Thank You</h3>
    <form action="ShowTicketsControllerServlet" method="get">
        <button type="submit" value="See" name="buy"/>
    </form>
    <%
        session.setAttribute("userID",session.getAttribute("userID"));
    %>
</body>
</html>
