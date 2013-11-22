<?PHP

#retreive information from Listings and <Category> tables. 

function retrieveFromAll() 
{
	$con = mysql_connect("localhost", "listAdmin", "hermes");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());
	$userID = $_SESSION['userID'];
	$listingID = $_GET['listingID'];
	$category = $_GET['category'];

	$listQuery = "SELECT title, dateListed, price, fullName, description FROM Listings";


	if($category=="Bikes")
	{
		$catQuery .= "SELECT bikeTypeID, make, model FROM Bikes WHERE listingID = '$listingID'";
	}
	
	if($category=="Books")
	{
		$catQuery .= "SELECT bookTypeID, title, author, isbn, assignedCourse, conditionID from Books WHERE listingID = '$listingID'";
	}

	if($category=="Electronics")
	{
		$catQuery .= "SELECT electronicsTypeID, make, model, size FROM Electronics WHERE listingID = '$listingID'";
	}

	if($category=="Furniture")
	{
		$catQuery .= "SELECT furnitureTypeID, conditionID FROM Furniture WHERE listingID = '$listingID'";
	}

	if($category=="Meetups")
	{
		$catQuery .= "SELECT meetupTypeID, location, date, time FROM Meetups WHERE listingID = '$listingID'";
	}

	if($category=="Miscellaneous")
	{
		
	}

	if($category=="Rides")
	{
		$catQuery .= "SELECT leavingFrom, goingTo, departureDate, departureTime, returnDate, returnTime FROM Rides WHERE listingID = '$listingID'";
	}

/*	$query = "START TRANSACTION;\n";
	$query.=$listQuery . '\n';
	$query.=$catQuery . '\n';
	$query.="\nCOMMIT;"
	$ins=mysql_query($query);
	if(!$ins)
	{
		$message = 'Database insert failed: ' . mysql_error() . "\n";
    $message .= 'Whole statement: ' . $query;
    die($message);
*/
	}
}

?>
