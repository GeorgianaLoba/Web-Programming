<!DOCTYPE html>
<html>
<head>
    <title> Login </title>
    <link rel="stylesheet" href="style.css">
    <script src="jquery-2.0.3.js"></script>
    <script>
        $(document).ready(function () {
            $("#login").click(function () {
                $.post("getUser.php",
                    {
                        username: $("#username").val(),
                        password: $("#password").val()
                    },
                    function (data, status) {
                        if (data.indexOf("error") !== -1){
                            $("#resultdiv").css("color", "red");
                            $("#resultdiv").html("Username not in db...");
                        }
                        else{
                            window.location.replace("main.php");                        
                        }
                    });
            });
        });
        
    </script>
</head>
<body>
    <div class="container">
        <img id="icon" alt="icon" src="icon.png"></img>
        <form>    
            <label for="username">Enter username: </label>
            <input id="username" type="text" name="username">
            <label for="password">Enter password: </label>
            <input id="password" type="password" name="password">
            <input id="login" type="button" value="Login"/>
        </form>
        <div id="resultdiv"></div><br/>
    </div>
</body>
</html>