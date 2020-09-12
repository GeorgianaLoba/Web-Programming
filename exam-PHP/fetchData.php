<?php
    include_once 'connect.php';

    if (!$handler){
        die("Could not connect to the database: ".mysqli_error());
    }

    $id = $_SESSION["id"];

    $sql = "SELECT * FROM files where userid='$id';";
    $result = mysqli_query($handler, $sql);
    $alldata = array();
    $jsonData = array();
    while ($row = mysqli_fetch_array($result)){
        $jsonData['id']=$row['id'];
        $jsonData['userid']=$row['userid'];
        $jsonData['filename']=$row['filename'];
        $jsonData['filepath']=$row['filepath'];
        $jsonData['size']=$row['size'];
        $alldata[] = $jsonData;
    }
    echo json_encode($alldata);
    mysqli_close($handler);
?>