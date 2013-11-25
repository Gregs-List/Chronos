<?PHP
session_start();
#inserts into Listings and <Category> table. Receives values from JavaScript
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
	$price = $_POST['price'];

/*	$last = mysql_query("SELECT photoID FROM Photos ORDER BY DESC LIMIT 1"); 
	$lastPhoto = mysql_fetch_array($last, MYSQL_ASSOC); 		
	$lastPhotoID = $lastPhoto['photoID'];
	$photoID = $lastPhotoID + 1;
*/
	$listQuery = "INSERT INTO Listings VALUES(NULL, '$userID', '$title',NULL, '$category', '$price', '$description')";


	if($category=="Bikes")
	{
		$catQuery = "INSERT INTO Bikes VALUES(last_insert_id(), '$_POST[bikeTypeID]', '$_POST[bMake]', '$_POST[bModel]')";
	}
	
	if($category=="Books")
	{
		$catQuery = "INSERT INTO Books VALUES(last_insert_id(), '$_POST[bookTypeID]', '$_POST[bookTitle]', '$_POST[author]', '$_POST[isbn]', '$_POST[assignedCourse]', '$_POST[bookCondition]')";
	}

	if($category=="Electronics")
	{
		$catQuery = "INSERT INTO Electronics VALUES(last_insert_id(), '$_POST[electronicsTypeID]', '$_POST[eMake]', '$_POST[eModel]', NULL)";
	}

	if($category=="Furniture")
	{
		$catQuery = "INSERT INTO Furniture VALUES(last_insert_id(), '$_POST[furnitureTypeID]', '$_POST[furnitureCondition]')";
	}

	if($category=="Meetups")
	{
		$catQuery = "INSERT INTO Meetups VALUES(last_insert_id(), '$_POST[meetupTypeID]', '$_POST[location]', '$_POST[date]', '$_POST[time]')";
	}

	if($category=="Miscellaneous")
	{
		$catQuery = "INSERT INTO Miscellaneous VALUES(last_insert_id(),NULL)";
	}

	if($category=="Rides")
	{
		$catQuery = "INSERT INTO Rides VALUES(last_insert_id(), '$_POST[leavingFrom]', '$_POST[goingTo]', '$_POST[departureDate]', '$_POST[departureTime]', '$_POST[returnDate]', '$_POST[returnTime]')";
	}


	/*$allowedExts = array("gif", "jpeg", "jpg", "png");
	$temp = explode(".", $_FILES["file"]["name"]);
	$extension = end($temp);
	if(!empty($_FILES["file"])){
	echo 'test';
}
	if ((($_FILES["file"]["type"] == "image/gif")
	|| ($_FILES["file"]["type"] == "image/jpeg")
	|| ($_FILES["file"]["type"] == "image/jpg")
	|| ($_FILES["file"]["type"] == "image/pjpeg")
	|| ($_FILES["file"]["type"] == "image/x-png")
	|| ($_FILES["file"]["type"] == "image/png"))
	&& ($_FILES["file"]["size"] < 200000)
	&& in_array($extension, $allowedExts))
  {	
  	echo 'test';
  if ($_FILES["file"]["error"] > 0)
    {
    echo "Return Code: " . $_FILES["file"]["error"] . "<br>";
    }
  else
    {
    if (file_exists("upload/" . $photoID))
      {
      echo $_FILES["file"]["name"] . " already exists. ";
      }
    else
      {
	$photoUrl = $photoID . $_FILES["file"]["type"];
      move_uploaded_file($_FILES["file"]["tmp_name"],
      "userUploads/" . $photoUrl);
	$fileQuery = "INSERT INTO Photos VALUES (last_insert_id(), '$photoID', '$photoUrl')";
	mysql_query($fileQuery);
      echo "Stored in: " . "userUploads/" . $photoUrl;
      }
    }
  }
else
  {
  echo "Invalid file";
  }
*/
	// begin insert transaction
	mysql_query("SET AUTOCOMMIT=0");
	mysql_query("START TRANSACTION");

	// insert into Listings
	$query=$listQuery;
	$ins=mysql_query($query);
	if(!$ins)
	{
		$message = 'Insert into Listings table failed: ' . mysql_error() . '<br>';
    $message .= 'Whole statement: ' . $query;
    die($message);
	}

	// insert into [category]
	$query=$catQuery;
	$ins=mysql_query($query);
	if(!$ins)
	{
		$message = "Insert into $category failed: " . mysql_error() . '<br>';
    $message .= 'Whole statement: ' . $query;
    die($message);
	}

	// commit changes
	$query="COMMIT";
	$ins=mysql_query($query);
	if(!$ins)
	{
		$message = 'Transaction failed: ' . mysql_error() . '<br>';
    $message .= 'Whole statement: ' . $query;
    die($message);
	}
	//header('Location: home.html');
?>
