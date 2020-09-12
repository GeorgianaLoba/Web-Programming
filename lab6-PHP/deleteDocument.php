<?php
    include_once 'connect.php';

    if (!$handler) {
        die('Could not connect to the database: ' . mysqli_error($handler));
    
    }
    $id = $_POST['id'];
    $myid= intval($id);
    $sql="delete from docs where id='$myid';";
    if (mysqli_query($handler, $sql)){
        echo "DELETED!";
    }else{
        echo "Error:".mysqli_error($handler);
    }
    mysqli_close($handler);
?>
