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
    <title>Register</title>
    <link rel="stylesheet"href="index.css">
</head>
<body>
<form name="Register Form" action="/servlet/register" method="POST">
    <label for="username">Username: </label></br>
    <input type="text" id="username" name="username"/></br>
    <label for="password">Password:</label></br>
    <input type="password" id="password" name="password"/></br>
    <label for="againPassword">Retype Password:</label></br>
    <input type="password" id="againPassword" name="againPassword"/></br>
    <label for="mail">Enter Mail:</label></br>
    <input type="email" id="mail" name="mail"></br>
    <input type="submit" value="Register"/>
</form>
</body>
</html>