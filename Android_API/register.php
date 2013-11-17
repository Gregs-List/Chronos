<?php
$con = mysql_connect("localhost", "listAdmin", "hermes");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());

	$query = "SELECT email FROM Users WHERE email='$_POST[email]'";

	$fname = $_POST['fname'];
	$lname = $_POST['lname'];
	$full_name = $fname . " " . $lname;
	$result = mysql_query($query);
	if (mysql_num_rows($result) > 0){
		echo "error";
	}
	else {
	$query = "INSERT INTO Users(userID, email, pword, fullName) 
		VALUES(NULL,'$_POST[email]','$_POST[password]','$full_name')";
	mysql_query($query);
		echo $query;
	}
?>