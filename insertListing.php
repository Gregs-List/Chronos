<?PHP

#inserts into Listings and <Category> table. Receives values from JavaScript
function insertIntoAll($userID, $title, $category, $description, $itemName) 
{
	$con = mysql_connect("localhost", "listAdmin", "hermes");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());

	$listQuery = "INSERT INTO Listings VALUES(NULL, '$userID', '$title',NULL, '$category', '$desciption')";


	if($category=="Bikes")
	{
		$catQuery .= "INSERT INTO Bikes VALUES(last_insert_id(), '$_GET[bikeTypeID]', '$_GET[make]', '$_GET[model]', '$_GET[itemName]'); \n";
	}
	
	if($category=="Books")
	{
		$catQuery .= "INSERT INTO Books VALUES(last_insert_id(), '$_GET[bookTypeID]', '$_GET[title]', '$_GET[author]', '$_GET[isbn]', '$_GET[assignedCourse]', '$_GET[conditionID]'); \n";
	}

	if($category=="Electronics")
	{
		$catQuery .= "INSERT INTO Electronics VALUES(last_insert_id(), '$_GET[electronicsTypeID]', '$_GET[make]', '$_GET[model]', '$_GET[size]'); \n";
	}

	if($category=="Furniture")
	{
		$catQuery .= "INSERT INTO Bikes VALUES(last_insert_id(), '$_GET[furnitureTypeID]', '$_GET[conditionID]'); \n";
	}

	if($category=="Meetups")
	{
		$catQuery .= "INSERT INTO Meetups VALUES(last_insert_id(), '$_GET[meetupTypeID]', '$_GET[location]', '$_GET[date]', '$_GET[time]'); \n";
	}

	if($category=="Miscellaneous")
	{
		$catQuery .= "INSERT INTO Miscellaneous VALUES(last_insert_id(), '$_GET[itemName]'); \n";
	}

	if($category=="Rides")
	{
		$catQuery .= "INSERT INTO Rides VALUES(last_insert_id(), '$_GET[leavingFrom]', '$_GET[goingTo]', '$_GET[departureDate]', '$_GET[departureTime]', '$_GET[returnDate]', '$_GET[returnTime]'); \n";
	}

	$query = "START TRANSACTION;\n";
	$query.=$listQuery . '\n';
	$query.=$catQuery . '\n';
	$query.="\nCOMMIT;"
	$ins=mysql_query($query);
	if(!$ins)
	{
		$message = 'Database insert failed: ' . mysql_error() . "\n";
    $message .= 'Whole statement: ' . $query;
    die($message);
	}
}

?>
