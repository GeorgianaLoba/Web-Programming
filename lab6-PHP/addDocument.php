<?php
    include_once 'connect.php';

    if (!$handler){
        die("Could not connect to the database: ".mysqli_error());
    }

    $title = $_POST['title'];
    $author =$_POST['author'];
    $numberPages = $_POST['numberPages'];
    $numberPages = intval($numberPages);
    $type = $_POST['type'];
    $format = $_POST['format'];

    $sql = "insert into docs(title, author, numberPages, type, format) VALUES ('$title', '$author', '$numberPages', '$type', '$format');";
    if (mysqli_query($handler, $sql)){
        echo "ADDED!";
    }else{
        echo "Error:".mysqli_error($handler);
    }
    mysqli_close($handler);
?>