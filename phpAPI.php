<?php

#Calls insertIntoMiscellaneous if these attributes are set
if(isset ($_GET['userID'] && $_GET['title'] && $_GET['category'] && $_GET['description'] && $_GET['itemName'])
{
	$userID = $_GET['userID'];
	$title = $_GET['title'];
	$category = $_GET['category'];
	$description = $_GET['description'];
	$itemName = $_GET['itemName'];
	insertIntoMiscellaneous($userID, $title, $category, $description,$itemName);
}
#Calls returnUserListings if these attributes are set
if(isset ($_GET['userID'])
{
	$userID = $_GET['userID'];
	returnUserListings($userID);
}

#inserts into Listings and Miscellaneous table. Receives values from JavaScript
function insertIntoMiscellaneous($userID, $title, $category, $description, $itemName) 
{
	$con = mysql_connect("localhost", "listAdmin", "hermes");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());

	$query = "INSERT INTO Listings VALUES(NULL, '$userID', '$title',NULL, '$category', '$desciption')";

	$query = "INSERT INTO Miscellaneous VALUES(LAST_INSERT_ID(), '$itemName')";
	mysql_query($query);
}

#returns JSON of all of a users listings. INCOMPLETE...only works for miscellaneous right now. Should be fine for Iteration 1
function returnUserListings($userID) #return short listing: title, category, datelisted
{
	$con = mysql_connect("localhost", "listAdmin", "hermes");
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

/* Update user info:
Check to see if a field is filled out--
If so, change value in that user's row */
function updateUserInfo()
	{
		$con = mysql_connect("localhost", "listAdmin", "hermes");
    if (!$con)
    {
       die('Could not connect: ' . mysql_error());
    }
    mysql_select_db("GregsList", $con)
       or die("Unable to select database:" . mysql_error());

		if(isset ($_POST['fullName'])
		{
			$fullName = $_POST['fullName'];
			$query = "UPDATE Users SET fullName='$fullName' WHERE userID='$userID'";
			mysql_query($query);
		}

		if(isset ($_POST['location'])
		{
			$location = $_POST['location'];
			$query = "UPDATE Users SET location='$location' WHERE userID='$userID'";
			mysql_query($query);
		}

		if(isset ($_POST['phone'])
		{
			$phone = $_POST['phone'];
			$query = "UPDATE Users SET phoneNumber='$phone' WHERE userID='$userID'";
			mysql_query($query);
		}

		if(isset ($_POST['password'])
		{
			$password = $_POST['password'];
			$query = "UPDATE Users SET pword='$password' WHERE userID='$userID'";
			mysql_query($query);
		}
		mysql_close($con)
	}
?>
