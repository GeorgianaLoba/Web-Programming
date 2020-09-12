<?php
    include_once 'connect.php';


    if (!$handler){
        die("Could not connect to the database: ".mysqli_error());
    }

    $sql = "SELECT * FROM docs";
    $result = mysqli_query($handler, $sql);
    $alldata = array();
    $jsonData = array();
    while ($row = mysqli_fetch_array($result)){
        $jsonData['id']=$row['id'];
        $jsonData['title']=$row['title'];
        $jsonData['author']=$row['author'];
        $jsonData['numberPages']=$row['numberPages'];
        $jsonData['type']=$row['type'];
        $jsonData['format']=$row['format'];
        $alldata[]= $jsonData;
    }
    echo json_encode($alldata);
    mysqli_close($handler);
?>