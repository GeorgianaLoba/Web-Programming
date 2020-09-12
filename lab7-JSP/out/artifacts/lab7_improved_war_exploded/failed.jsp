<%--
  Created by IntelliJ IDEA.
  User: Geo
  Date: 5/15/2020
  Time: 9:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Failed</title>
    <link rel="stylesheet" href="/failed.css">
</head>
<body>
<div>
<h4>Something went wrong...</h4>
<%
    String errors = (String)session.getAttribute("errors");
    out.println(errors);
%>
    </div>
</body>
</html>
