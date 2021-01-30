<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <link rel="stylesheet" href="tickets.css">
</head>
<body>
<h2> نتایج جستجو </h2>
<hr>
<br/>

<table border="1">
    <tr>
        <th>شناسه سفر </th>
        <th>مبدا </th>
        <th>مقصد </th>
        <th>تاریخ حرکت</th>
        <th>زمان حرکت</th>
    </tr>

    <c:forEach var="tempTravel" items="${travelList}">
        <form action="TicketControllerServlet" method="get">
        <tr>
            <td>${tempTravel.travelId}</td>
            <td>${tempTravel.travelFrom}</td>
            <td>${tempTravel.travelTo}</td>
            <td>${tempTravel.date}</td>
            <td>${tempTravel.time}</td>
            <td><button type="submit" name="travelId" value="${tempTravel.id}"/>خرید بلیط</td>
        </tr>
    </c:forEach>
        </form>
</table>
            <%
                 session.setAttribute("userN",session.getAttribute("username"));
            %>

    <br/><br/>
    <form action="travel.html">
        <input type="submit" value="بازگشت" />
    </form>
</body>
</html>
