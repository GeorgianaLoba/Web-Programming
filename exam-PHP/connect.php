<?php
    session_start();
    $server="127.0.0.1";
    $user="root";
    $password="";
    $database="redo";
    
    $handler = mysqli_connect($server, $user, $password, $database);
?>