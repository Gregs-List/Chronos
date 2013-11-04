<?php
	session_start();
	$con = mysql_connect("localhost", "listAdmin", "hermes");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());

	$result = mysql_query("SELECT * FROM Listings WHERE userID = 2 ORDER BY dateListed DESC");

	#converts to json
	$rows = array();
	while($r = mysql_fetch_assoc($result)) 
	{
    		$rows[] = $r;
	}

	#If you want to see if correct json is printing use ---> print json_encode($rows);
	
	return json_encode($rows);
?>
