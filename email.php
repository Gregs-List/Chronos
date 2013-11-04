<?php
	session_start();
	$con = mysql_connect("localhost","listAdmin","hermes");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());
	$query = "SELECT email FROM Users WHERE userID='$_SESSION[userID]'";
	$result = mysql_query($query);
	while ($row = mysql_fetch_assoc($result)){
		$email = $row['email'];
	}
	echo $email;

	mysql_close($con);
	?>