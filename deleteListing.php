<?PHP

// deletes from Listings and <Category> table. 
// Note: Do we want to find the appropriate category table and delete from it only, or search and delete from all tables with the given listingID?

function deleteListing($listingID) 
{
	$con = mysql_connect("localhost", "listAdmin", "hermes");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());
	$userID = $_SESSION['userID'];
	$category = mysql_query("SELECT category FROM Listings WHERE listingID = $listingID;");
	$deletePhotos = "DELETE FROM Photos WHERE listingID = $listingID;";
	$deleteCat = "DELETE FROM $category WHERE listingID = $listingID;";
	$deleteList = "DELETE FROM Listings WHERE listingID = $listingID;";

	$deleteAll = "START TRANSACTION;\n" . $deletePhotos . '\n' . $deleteCat . '\n' . $deleteList . "\nCOMMIT;";

	$success=mysql_query($deleteAll);
	if(!$success)
	{
		$message = 'Database insert failed: ' . mysql_error() . "\n";
		$message .= 'on SQL statement: ' . $deleteAll;
		die($message);
	}

}

?>
