<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<body>
<h2> Search Result</h2>
<hr>
<br/>

<table border="1">
    <tr>
        <th>Travel Id</th>
        <th>Travel From</th>
        <th>Travel To </th>
        <th>Date</th>
        <th>Time</th>
    </tr>

    <c:forEach var="tempTravel" items="${travelList}">
        <form action="TicketControllerServlet" method="get">
        <tr>
            <td>${tempTravel.travelId}</td>
            <td>${tempTravel.travelFrom}</td>
            <td>${tempTravel.travelTo}</td>
            <td>${tempTravel.date}</td>
            <td>${tempTravel.time}</td>
            <td><button type="submit" name="travelId" value="${tempTravel.id}"/>Buy</td>
        </tr>
    </c:forEach>
        </form>
</table>
            <%
                 session.setAttribute("userN",session.getAttribute("username"));
            %>
</body>
</html>
