<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="delete.css" rel="stylesheet">
</head>

<body>
<br /><br />
<h2 style="float:right;text-align:right;margin-right:30px;">تراکنش موفق</h2>
<br /><br />
<br /><br />

<form action="ShowTicketsControllerServlet" method="get">
    <button type="submit" value="See" name="buy" style="float:right;text-align:right;margin-right:30px;">بلیط های من</button>
</form>
        <% session.setAttribute("userID",session.getAttribute("userID")); %>
</body>

</html>