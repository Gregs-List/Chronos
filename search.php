<?php
	session_start();
	$con = mysql_connect("localhost", "listAdmin", "hermes");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());

	#Full text index??
	#$search = $_POST['search'];
	$query = "SELECT * FROM Listings WHERE title or description LIKE '%iPhone 4%'";
	$result = mysql_query($query);

	#converts to json
	$rows = array();
	while($r = mysql_fetch_assoc($result)) 
	{
    		$rows[] = $r;
	}

	#If you want to see if correct json is printing use ---> print json_encode($rows);
	print json_encode($rows);
	#return json_encode($rows);

	function sortByCategory()
	{
		$category = $_POST['category']; 
		$query = "SELECT * FROM Listings WHERE category='$category' and title or description LIKE '%iPhone 4%'";
		$result = mysql_query($query);
		
		#converts to json
		$rows = array();
		while($r = mysql_fetch_assoc($result)) 
		{
    			$rows[] = $r;
		}
	}

?>
