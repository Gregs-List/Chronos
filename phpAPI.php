<?php

#Calls insertIntoMiscellaneous if these attributes are set
if(isset ($_POST['userID'] && $_POST['title'] && $_POST['category'] && $_POST['description'] && $_POST['itemName'])
{
	$userID = $_POST['userID'];
	$title = $_POST['title'];
	$category = $_POST['category'];
	$description = $_POST['description'];
	$itemName = $_POST['itemName'];
	insertIntoMiscellaneous($userID, $title, $category, $description,$itemName);
}
#Calls returnUserListings if these attributes are set
if(isset ($_POST['userID'])
{
	$userID = $_POST['userID'];
	returnUserListings($userID);
}

#inserts into Listings and Miscellaneous table. Receives values from JavaScript
function insertIntoMiscellaneous($userID, $title, $category, $description, $itemName) 
{
	$con = mysql_connect("localhost", "gregslist", "greg");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());

	$query = "INSERT INTO Listings VALUES(NULL, '$userID', '$title',NULL, '$category', '$desciption')";

	$query = "INSERT INTO Miscellaneous VALUES(mysql_insert_id(), '$itemName')";
	mysql_query($query);
}

#returns JSON of all of a users listings. INCOMPLETE...only works for miscellaneous right now. Should be fine for Iteration 1
function returnUserListings($userID) #return short listing: title, category, datelisted
{
	$con = mysql_connect("localhost", "gregslist", "greg");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());

	$result = mysql_query("SELECT * FROM Listings WHERE userID = '$userID' ORDER BY dateListed DESC");

	#converts to json
	$rows = array();
	while($r = mysql_fetch_assoc($result)) 
	{
    		$rows[] = $r;
	}

	#If you want to see if correct json is printing use ---> print json_encode($rows);
	
	return json_encode($rows);
}


/* Get some number of most recent listings, 
order by most to least recent, 
encode to JSON and print */
function getRecentListings()	#incomplete
  {
    $con = mysql_connect("localhost", "listAdmin", "hermes");
    if (!$con)
    {
       die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("GregsList", $con)
       or die("Unable to select database:" . mysql_error());
	}


/*function insertIntoListings($listingID, $userID, $title, $dateListed, $category, $description)
{
	$con = mysql_connect("localhost", "gregslist", "greg");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());

	$query = "INSERT INTO Listings VALUES('$listingID', $userID', '$title', '$dateListed', 							'$category', '$description')";
	mysql_query($query); 
}*/

?>
