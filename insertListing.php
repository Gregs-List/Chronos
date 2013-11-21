<?PHP

#inserts into Listings and <Category> table. Receives values from JavaScript
function insertIntoAll() 
{
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

	$listQuery = "INSERT INTO Listings VALUES(NULL, '$userID', '$title',NULL, '$category', '$desciption')";


	if($category=="Bikes")
	{
		$catQuery = "INSERT INTO Bikes VALUES(last_insert_id(), '$_POST[bikeTypeID]', '$_POST[make]', '$_POST[model]'); \n";
	}
	
	if($category=="Books")
	{
		$catQuery = "INSERT INTO Books VALUES(last_insert_id(), '$_POST[bookTypeID]', '$_POST[title]', '$_POST[author]', '$_POST[isbn]', '$_POST[assignedCourse]', '$_POST[conditionID]'); \n";
	}

	if($category=="Electronics")
	{
		$catQuery = "INSERT INTO Electronics VALUES(last_insert_id(), '$_POST[electronicsTypeID]', '$_POST[make]', '$_POST[model]', '$_POST[size]'); \n";
	}

	if($category=="Furniture")
	{
		$catQuery = "INSERT INTO Bikes VALUES(last_insert_id(), '$_POST[furnitureTypeID]', '$_POST[conditionID]'); \n";
	}

	if($category=="Meetups")
	{
		$catQuery = "INSERT INTO Meetups VALUES(last_insert_id(), '$_POST[meetupTypeID]', '$_POST[location]', '$_POST[date]', '$_POST[time]'); \n";
	}

	if($category=="Miscellaneous")
	{
		$catQuery = "INSERT INTO Miscellaneous VALUES(last_insert_id(), '); \n";
	}

	if($category=="Rides")
	{
		$catQuery = "INSERT INTO Rides VALUES(last_insert_id(), '$_POST[leavingFrom]', '$_POST[goingTo]', '$_POST[departureDate]', '$_POST[departureTime]', '$_POST[returnDate]', '$_POST[returnTime]'); \n";
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
