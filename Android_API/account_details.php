<?php
	session_start();
	$con = mysql_connect("localhost","listAdmin","hermes");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());
	$query = "SELECT email,phoneNumber FROM Users WHERE userID='$_GET[userID]'";
	$result = mysql_query($query);
	while ($row = mysql_fetch_assoc($result)){
		$email = $row['email'];
		$phone = $row['phoneNumber'];
	}

    $array = array("email"=>$email,"phone"=>$phone);
    $json = json_encode($array);
    echo $json;
	mysql_close($con);
	?>