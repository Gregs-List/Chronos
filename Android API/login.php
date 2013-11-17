<?php

    $con = mysql_connect("localhost", "listAdmin", "hermes");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}

    mysql_select_db("GregsList", $con) 
        or die("Unable to select database:" . mysql_error());

    $password = $_GET['password'];
    $email = $_GET['email'];

    $query = "select * from Users where email = '$email'";
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
   		echo 'error';
    }
    else{
   		echo "Name: " . $name . "\r\n" . "Location: " . $location . "\r\n" . 
        "Phone: " . $phoneNumber;
        }
    mysql_close($con);
    ?>