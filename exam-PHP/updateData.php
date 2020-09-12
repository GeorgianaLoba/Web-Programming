<?php
    include_once 'connect.php';

    if (!$handler){
        die("Could not connect to the database: ".mysqli_error());
    }

    $username = $_SESSION['username'];
    $date = date('Y-m-d');

    $id1 = $_POST['postid'];
    $postid=intval($id1);
    $id2 = $_POST['topicid'];
    $topicid=intval($id2);
    $text = $_POST['text'];
    $sql = "update posts set topicid='$topicid', text='$text', user='$username', date='$date' where id='$postid';";
    if (mysqli_query($handler, $sql)){
        echo "UPDATED!";
    }else{
        echo "Error:".mysqli_error($handler);
    }
    mysqli_close($handler);

?>