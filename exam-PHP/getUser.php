<?php
    include_once 'connect.php';

    if (!$handler){
        die("Could not connect to the database: ".mysqli_error());
    }

    $username = $_POST['username'];
    $password =$_POST['password'];

    $sql = "select * from users where username ='$username' and password='$password';";
    $result =  mysqli_query($handler, $sql);
    $count = mysqli_num_rows($result);
    if ($count < 1) echo "error";
    $array = mysqli_fetch_array($result);
    $_SESSION['id'] = $array['id'];
?>