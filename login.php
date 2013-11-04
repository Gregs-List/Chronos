<?php
	$con = mysql_connect("localhost", "gregslist", "greg");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	//Function to check the log in of a user
	function login(){
    echo 'meow';
	$query = "select userID from Users where email ='$_GET[email]'";
	$result = mysql($query);
	while ($row = mysql_fetch_assoc($id_query_result)){
        $id = $row ['userID'];
    }
    session_start();
    $_SESSION['userID']=$id;
	$query = "select pword from Users where userID ='$id'";
    $result = mysql_query($query);
    while ($row = mysql_fetch_assoc($result)){
    	$database_password = $row ['pword'];
    }
    if($_GET['password'] != $database_password){
   		header()//Enter webpage to show if the login credentials are invalid
    }
    else{
   		header()//Enter webpage to show if login credentials are valid
        }
    mysql_close($con);
	}
?>