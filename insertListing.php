<?PHP

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
	$itemName = $_POST['itemName'];
	
	$last = mysql_query("SELECT photoID FROM Photos ORDER BY DESC 		LIMIT 1;"); 
	$lastPhoto = mysql_fetch_array($last, MYSQL_ASSOC); 		$lastPhotoID = $lastPhoto['photoID'];
	$photoID = $lastPhotoID + 1;

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


	if($_FILES["photos"]["name"] != NULL){
	$allowedExts = array("gif", "jpeg", "jpg", "png");
	$temp = explode(".", $_FILES["photos"]["name"]);
	$extension = end($temp);
	if ((($_FILES["photos"]["type"] == "image/gif")
	|| ($_FILES["photos"]["type"] == "image/jpeg")
	|| ($_FILES["photos"]["type"] == "image/jpg")
	|| ($_FILES["photos"]["type"] == "image/pjpeg")
	|| ($_FILES["photos"]["type"] == "image/x-png")
	|| ($_FILES["photos"]["type"] == "image/png"))
	&& ($_FILES["photos"]["size"] < 20000)
	&& in_array($extension, $allowedExts))
  {
	$last = mysql_query("SELECT photoID FROM Photos ORDER BY DESC 		LIMIT 1;"); 
	$lastPhoto = mysql_fetch_array($last, MYSQL_ASSOC); 		$lastPhotoID = $lastPhoto['photoID'];
	$photoID = $lastPhotoID + 1;
  if ($_FILES["photos"]["error"] > 0)
    {
    echo "Return Code: " . $_FILES["photos"]["error"] . "<br>";
    }
  else
    {
    if (file_exists("upload/" . $photoID))
      {
      echo $_FILES["photos"]["name"] . " already exists. ";
      }
    else
      {
	$photoUrl = $photoID . $_FILES["photos"]["type"];
      move_uploaded_file($_FILES["photos"]["tmp_name"],
      "userUploads/" . $photoUrl);
	$fileQuery = "INSERT INTO Photos VALUES (last_insert_id(), 		'$photoID', '$photoUrl')";
	mysql_query($fileQuery);
      echo "Stored in: " . "userUploads/" . $photoUrl;
      }
    }
  }
else
  {
  echo "Invalid file";
  }
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

?>
