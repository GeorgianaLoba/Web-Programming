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
    <title>Image Puzzle</title>
    <link rel="stylesheet" href="/main.css">
    <script src="/jquery-2.0.3.js"></script>
    <script src="/puzzle.js"></script>
</head>
<body onload="showPuzzle()">
<div id="user">
    <h4>Currently playing as: <p id="username"><%String username = (String)session.getAttribute("username");out.println(username);%></p></h4>
    <h4>Number of moves done: <p id="moves"></p></h4>
    <form action="/servlet/done" method="POST" onSubmit="return confirm('Are you sure you wish to restart the game?')">
        <input id="uname" name="uname" style="display: none" value=<%out.println(username);%>>
        <input id="status" name="status" style="display: none" value="abandoned">
        <input type="submit" value="Restart Game">
    </form>
</div>
<div id="puzzle">
    <table>
        <tr>
            <td id="1"></td>
            <td id="2"></td>
            <td id="3"></td>
        </tr>
        <tr>
            <td id="4"></td>
            <td id="5"></td>
            <td id="6"></td>
        </tr>
        <tr>
            <td id="7"></td>
            <td id="8"></td>
            <td id="9"></td>
        </tr>
    </table>
</div>
</body>
</html>
