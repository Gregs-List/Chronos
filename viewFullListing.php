<?PHP

# retreive information from Listings and <Category> tables.
# future work:
# if statements to prevent subtitles for null fields

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

// Query database for Listing information
	$listQuery = "SELECT title, dateListed, price, description FROM Listings";
	$result1 = mysql_query($listQuery);
	if(!$result1)
	{
		$message = 'Database query failed: ' . mysql_error() . "\n";
    $message .= 'Query: ' . $listQuery;
    die($message);
	}
// store results in an array
	$listing = mysql_fetch_array($result1, MYSQL_ASSOC);
	$fullName = mysql_query("SELECT fullName FROM Users WHERE userID = $userID;");
// print results to screen
	echo "Title: {$listing[title]}<br>
Date listed: {$listing[dateListed]}<br>
Price: {$listing[price]}<br>
Contact: $fullName<br>";


// Print Bike information, if Bike
	if($category=="Bikes")
	{
	$catQuery .= "SELECT bikeTypeID, make, model FROM Bikes WHERE listingID = '$listingID'";
	$result2 = mysql_query($catQuery);
//  error if query fails
	if(!$result2)
	{
		$message = 'Database query failed: ' . mysql_error() . "\n";
    $message .= 'Query: ' . $catQuery;
    die($message);
	}
// Convert to array and print
	$cat = mysql_fetch_array($result2, MYSQL_ASSOC);
	$bikeType = mysql_query("SELECT bikeType from BikeType where bikeTypeID = {$cat[bikeTypeID]};");
	echo "Type: $bikeType<br>
Make: {$cat[make]}<br>
Model: {$cat[model]}<br>";
	}


// Print Book information, if Book	
	if($category=="Books")
	{
	$catQuery .= "SELECT bookTypeID, title, author, isbn, assignedCourse, conditionID from Books WHERE listingID = $listingID;";
	$result2 = mysql_query($catQuery);
//  error if query fails
	if(!$result2)
	{
		$message = 'Database query failed: ' . mysql_error() . "\n";
    $message .= 'Query: ' . $catQuery;
    die($message);
	}
// convert to array and print
	$cat = mysql_fetch_array($result2, MYSQL_ASSOC);
	$bookType = mysql_query("SELECT bookType from BookType where bookTypeID = {$cat[bookTypeID]};");
	$condition = mysql_query("SELECT itemCondition FROM ConditionLookup where conditionID = {$cat[conditionID]};");
	echo "Type: $bookType<br>
Book title: {$cat[title]}<br>
Author: {$cat[author]}<br>
ISBN: {$cat[isbn]}<br>
Assigned Course: {$cat[assignedCourse]}<br>
Condition: $condition<br>";
	}


// Print Electronics information, if Electronics
	if($category=="Electronics")
	{
		$catQuery .= "SELECT electronicsTypeID, make, model, size FROM Electronics WHERE listingID = '$listingID'";
	$result2 = mysql_query($catQuery);
//  error if query fails
	if(!$result2)
	{
		$message = 'Database query failed: ' . mysql_error() . "\n";
    $message .= 'Query: ' . $catQuery;
    die($message);
	}
// convert to array and print
	$cat = mysql_fetch_array($result2, MYSQL_ASSOC);
	$electronicsType = mysql_query("SELECT electronicsType FROM ElectronicsType WHERE electronicsTypeID = {$cat[electronicsType]};");
	echo "Type: $electronicsType<br>
Make: {$cat[make]}<br>
Model: {$cat[model]}<br>
Size: {$cat[size]}<br>";
	}


// Print Furniture, if furniture
	if($category=="Furniture")
	{
		$catQuery .= "SELECT furnitureTypeID, conditionID FROM Furniture WHERE listingID = '$listingID'";
	$result2 = mysql_query($catQuery);
//  error if query fails
	if(!$result2)
	{
		$message = 'Database query failed: ' . mysql_error() . "\n";
    $message .= 'Query: ' . $catQuery;
    die($message);
	}
// convert to array and print
	$cat = mysql_fetch_array($result2, MYSQL_ASSOC);
	$furnitureType = mysql_query("SELECT furnitureType FROM FurnitureType WHERE furnitureTypeID = {$cat[furnitureTypeID]};");
	$condition = mysql_query("SELECT itemCondition FROM ConditionLookup where conditionID = {$cat[conditionID]};");
	echo "Type: $furnitureType<br>
Condition: $condition<br>";
	}


// Print Meetups information, if Meetup
	if($category=="Meetups")
	{
	$catQuery .= "SELECT meetupTypeID, location, date, time FROM Meetups WHERE listingID = '$listingID'";
	$result2 = mysql_query($catQuery);
//  error if query fails
	if(!$result2)
	{
		$message = 'Database query failed: ' . mysql_error() . "\n";
    $message .= 'Query: ' . $catQuery;
    die($message);
	}
// convert to array and print
	$cat = mysql_fetch_array($result2, MYSQL_ASSOC);
	$meetupType = mysql_query("SELECT meetupType FROM MeetupType WHERE meetupTypeID = {$cat[meetupTypeID]};");
	echo "Purpose: $meetupType<br>
Location: {$cat[location]}<br>
Date: {$cat[date]}<br>
Time: {$cat[time]}<br>";

	}

	if($category=="Miscellaneous")
	{
		
	}

	if($category=="Rides")
	{
	$catQuery .= "SELECT leavingFrom, goingTo, departureDate, departureTime, returnDate, returnTime FROM Rides WHERE listingID = '$listingID'";
	$result2 = mysql_query($catQuery);
//  error if query fails
	if(!$result2)
	{
		$message = 'Database query failed: ' . mysql_error() . "\n";
    $message .= 'Query: ' . $catQuery;
    die($message);
	}
// convert to array and print
	$cat = mysql_fetch_array($result2, MYSQL_ASSOC);
	echo "Leaving from: {$cat[leavingFrom]}<br>
Going to: {$cat[goingTo]}<br>
Departure date: {$cat[departureDate]}<br>
Departure time: {$cat[departureTime]}<br>
Return date: {$cat[returnDate]}<br>
Return time: {$cat[returnTime]}<br>";
	}



//Print general description last
	echo "Description: $listing[description]<br><br>";
	
}

?>
