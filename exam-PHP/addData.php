<?php
    include_once 'connect.php';

    if (!$handler){
        die("Could not connect to the database: ".mysqli_error());
    }

    $username = $_SESSION['username'];
    $date = date('Y-m-d');
    $topicname = $_POST['topicname'];
    $text = $_POST['ptext'];



    $sql = "select * from topics where topicname ='$topicname';";
    $result =  mysqli_query($handler, $sql);
    $count = mysqli_num_rows($result);

    if ($count < 1){
        $anothersql = "insert into topics(topicname) VALUES ('$topicname');";

        if (mysqli_query($handler, $anothersql)){
            $getidsql  = "select * from topics where topicname ='$topicname';";
            $resultid =  mysqli_query($handler, $getidsql);
            $arrayid = mysqli_fetch_array($resultid);
            $topicid = intval($arrayid['id']);

        }else{
            echo "Error:".mysqli_error($handler);
        }
    }
    else{
        $array = mysqli_fetch_array($result);
        $topicid = intval($array['id']);
    }


    $sqladd = "insert into posts(user, topicid, text, date) VALUES ('$username', '$topicid', '$text', '$date');";
    if (mysqli_query($handler, $sqladd)){
        echo "ADDED!";
    }else{
        echo "Error:".mysqli_error($handler);
    }
    mysqli_close($handler);
?>