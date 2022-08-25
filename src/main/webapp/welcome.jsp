<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <%
     response.addHeader("Pragma", "no-cache");
     response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
   %>
</head>
<body>
<h1>Welcome <%= session.getAttribute("email") %> !</h1>
<a href="logout"/>Logout</a>
</table>
<body>
</html>