<?php
	$con = mysql_connect("localhost", "listAdmin", "hermes");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());

	#session_start();
	$session = $_SESSION['userID'];	
	echo $session;


	$result = mysql_query("SELECT * FROM Listings WHERE userID = '$session' ORDER BY dateListed DESC");
	
	#converts to json
	$rows = array();
	while($r = mysql_fetch_array($result)) 
	{
    		//$rows[] = $r;
		echo "Title:" . '&nbsp;&nbsp;&nbsp;&nbsp;' . $r['title'] . '&nbsp;&nbsp;&nbsp;&nbsp;' . "Date Posted:" . '&nbsp;&nbsp;&nbsp;&nbsp;' . $r['dateListed'];
		echo "<button id='editButton' type='button' value='{$r['listingID']}'>Edit</button>";
		echo "<br>";
	}

	#If you want to see if correct json is printing use ---> print json_encode($rows);
	
	//return json_encode($rows);*/
?>
