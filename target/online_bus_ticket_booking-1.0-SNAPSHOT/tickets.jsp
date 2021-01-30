
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><html>

<head>
    <link rel="stylesheet" href="tickets.css">
</head>

<body>
<p>
<h1>بلیط های من</h1>
</p>
<hr style="height:2px;border-width:0;color:gray;background-color:gray">
<br /><br />
<table>
    <br /><br />
    <thead>
    <tr>
        <th>شناسه سفر</th>
        <th> مبدا</th>
        <th>مقصد </th>
        <th>تاریخ حرکت</th>
        <th>زمان حرکت</th>
        <th>نام</th>
        <th>نام خانوادگی</th>
        <th>جنسیت</th>
        <th>شناسه بلیط</th>
    </tr>
    </thead>
    <c:forEach var="temp" items="${pList}">
    <form action="DeleteControllerServlet" method="get">
    <tbody>
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
            <td><button type="submit" name="travelId" value="${temp[2].id}" />حذف بلیط</td>
        </tr>
    </tbody>
    </c:forEach>
    </form>
</table>
<br /><br /><br /><br />
<a href="travel.html" style="float:right;text-align:right;margin-right:30px;">انتخاب سفر</a>
</body>

</html>