<?php

    $con = mysql_connect("localhost", "listAdmin", "hermes");
    if (!$con)
    {
        die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("GregsList", $con) 
        or die("Unable to select database:" . mysql_error());

        error_reporting(E_ALL);
        ini_set('display_errors', '1');
    //Function to check the log in of a user
    $query = "select userID from Users where email ='$_GET[email]'";
    $result = mysql_query($query);
    while ($row = mysql_fetch_assoc($result)){
        $id = $row['userID'];
    }
    session_start();
    $_SESSION['userID']=$id;
    $query = "select pword from Users where userID =$id";
    $result = mysql_query($query);
    while ($row = mysql_fetch_assoc($result)){
        $database_password = $row ['pword'];
    }
    if($_GET['password'] != $database_password){
        header('Location: index.html');//Enter webpage to show if the login credentials are invalid
    }
    else{
        header('Location: home.html');//Enter webpage to show if login credentials are valid
        }
    mysql_close($con);
?>