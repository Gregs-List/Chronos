<?php

    $con = mysql_connect("localhost", "listAdmin", "hermes");
    if (!$con)
    {
        die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("GregsList", $con) 
        or die("Unable to select database:" . mysql_error());

    $password = $_POST['password'];
    $email = $_POST['email'];

    echo $password;
    echo $email;

    $query = "select * from Users where email = '$email'";
    echo $query;
    
    $result = mysql_query($query);

    while ($row = mysql_fetch_assoc($result)){
        $id = $row['userID'];
        $name = $row['fullName'];
        $location = $row['location'];
        $phoneNumber = $row['phoneNumber'];
    }
    
    session_start();
    $_SESSION['userID']=$id;
    $query = "select pword from Users where userID ='$id'";
    $result2 = mysql_query($query);
    while ($row = mysql_fetch_assoc($result2)){
        $database_password = $row ['pword'];
    }
    if($password != $database_password){
    	header('Location:index.html?failed');
    }
    else{
        header('Location:home.php');
        //echo $id;
        }
    mysql_close($con);
    ?>