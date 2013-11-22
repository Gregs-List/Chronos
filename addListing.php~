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
	$title = $_GET['title'];
	$category = $_GET['category'];
	$description = $_GET['description'];
	$itemName = $_GET['itemName'];
	$query = "INSERT INTO Listings VALUES(NULL, '$_SESSION[userID]', 
		'$_GET[title]', NULL, '$_GET[category]', '$_GET[description]')";
	mysql_query($query);
	if(!query)
	{
		$message = 'Database insert failed: ' . mysql_error() . "\n";
    $message .= 'Whole query: ' . $query;
    die($message);
	}
	$query = "INSERT INTO Miscellaneous VALUES(last_insert_id(), '$_GET[itemName]')";
	mysql_query($query);
	if(!query)
	{
		$message = 'Database insert failed: ' . mysql_error() . "\n";
    $message .= 'Whole query: ' . $query;
    die($message);
	}
	Header('Location: home.html');
?>
