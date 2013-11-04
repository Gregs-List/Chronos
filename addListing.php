<?php

	session_start();
	$con = mysql_connect("localhost", "listAdmin", "hermes");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());
	$userID = $_SESSION['userID'];
	$title = $_POST['title'];
	$category = $_POST['category'];
	$description = $_POST['description'];
	$itemName = $_POST['itemName'];
	echo $_SESSION['userID'];
	$query = "INSERT INTO Listings VALUES(NULL, '$_SESSION[userID]', 
		'$_POST[title]', NULL, '$_POST[category]', '$_POST[description]')";
	mysql_query($query);
	$query = "INSERT INTO Miscellaneous VALUES(mysql_insert_id(), '$_POST[itemName]')";
	mysql_query($query);
?>