<?php
	session_start();
	$con = mysql_connect("localhost", "listAdmin", "hermes");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());


	$search = $_GET['search'];
	$category = $_GET['category']; 

	$query = "SELECT title, description, price, listingID FROM Listings WHERE category='$category' and (title LIKE '%$search%' or description LIKE '%$search%') ORDER BY dateListed DESC";

		
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
