<?PHP

// deletes from Listings and <Category> table. 
// Note: Do we want to find the appropriate category table and delete from it only, or search and delete from all tables with the given listingID?

	$con = mysql_connect("localhost", "listAdmin", "hermes");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());
	$listingID =$_GET['listingID'];	
	$category = mysql_query("SELECT category FROM Listings WHERE listingID = '$listingID'");
	$cat = mysql_fetch_array($category,MYSQL_ASSOC);
	$category=$cat['category'];

	$deletePhotos = "DELETE FROM Photos WHERE listingID = '$listingID'";
	$deleteCat = "DELETE FROM '$category' WHERE listingID = '$listingID'";
	$deleteList = "DELETE FROM Listings WHERE listingID = '$listingID'";
	mysql_query("START TRANSACTION");
	mysql_query($deletePhotos);
	mysql_query($deleteCat);
	mysql_query($deleteList);
	mysql_query("COMMIT");

	echo "Deleted";
	#header('Location: myAccount.php');


?>
