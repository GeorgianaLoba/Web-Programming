<?php
    include_once 'connect.php';

    if (!$handler){
        die("Could not connect to the database: ".mysqli_error());
    }

    
    $id = $_POST['id'];
    $id=intval($id);
    $title = $_POST['title'];
    $author =$_POST['author'];
    $numberPages = $_POST['numberPages'];
    $numberPages = intval($numberPages);
    $type = $_POST['type'];
    $format = $_POST['format'];

    $sql = "update docs set title='$title', author='$author', numberPages='$numberPages', type='$type', format='$format' where id='$id';";
    if (mysqli_query($handler, $sql)){
        echo "UPDATED!";
    }else{
        echo "Error:".mysqli_error($handler);
    }

    mysqli_close($handler);

?>