
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Tickets</h2>
<hr>
<br/>

<table border="1">

    <tr>
        <th>Travel Id</th>
        <th>Travel From</th>
        <th>Travel To </th>
        <th>Date</th>
        <th>Time</th>
        <th>FirstName</th>
        <th>Lastname</th>
        <th>Gender</th>
        <th>Ticket ID</th>
    </tr>

    <c:forEach var="temp" items="${pList}">
    <tr>
            <td>${temp[1].travelId}</td>
            <td>${temp[1].travelFrom}</td>
            <td>${temp[1].travelTo}</td>
            <td>${temp[1].date}</td>
            <td>${temp[1].time}</td>
            <td>${temp[0].firstName}</td>
            <td>${temp[0].lastName}</td>
            <td>${temp[0].gender}</td>
            <td>${temp[2].id}</td>
    </tr>
</c:forEach>
</table>
</form>
</body>
</html>
