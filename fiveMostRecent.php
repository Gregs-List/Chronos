<?php
	session_start();
	$con = mysql_connect("localhost", "listAdmin", "hermes");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());

	$query = "SELECT t1.title, t1.description, t1.price, t1.listingID, t2.photoURL FROM Listings t1 
	INNER JOIN Photos t2 ON t1.listingID = t2.listingID WHERE photoURL IS NOT NULL ORDER BY dateListed DESC LIMIT 5";
	$result = mysql_query($query);

		
	#converts to json
	$rows = array();
	while($r = mysql_fetch_assoc($result)) 
	{
    		$rows[] = $r;
	}

	echo json_encode($rows);
	#return json_encode($rows);
	
?>
