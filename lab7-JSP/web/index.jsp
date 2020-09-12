<%--
  Created by IntelliJ IDEA.
  User: Geo
  Date: 5/15/2020
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
  <link rel="stylesheet"href="index.css">
</head>
<body>
<h5>Welcome, please login here: </h5>
<form name="Login Form" action="/servlet/login" method="POST">
  <label for="username">Username: </label></br>
  <input type="text" id="username" name="username"/></br>
  <label for="password">Password:</label></br>
  <input type="password" id="password" name="password"/></br>
  <input type="submit" value="Log In"/>
</form>

<h5>No account? Press here to register: </h5>
<form name="Register Form" action="register.jsp">
  <input type="submit" value="Create New Account" >
</form>
</body>
</html>
