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
	$itemName = $_POST['itemName'];
	$price = $_POST['price'];

	echo $category;
	
	$last = mysql_query("SELECT photoID FROM Photos ORDER BY DESC LIMIT 1"); 
	$lastPhoto = mysql_fetch_array($last, MYSQL_ASSOC); 		
	$lastPhotoID = $lastPhoto['photoID'];
	$photoID = $lastPhotoID + 1;

	$listQuery = "INSERT INTO Listings VALUES(NULL, '$userID', '$title',NULL, '$category', '$price', '$desciption')";


	if($category=="Bikes")
	{
		$catQuery = "INSERT INTO Bikes VALUES(last_insert_id(), '$_POST[bikeTypeID]', '$_POST[make]', '$_POST[model]')";
	}
	
	if($category=="Books")
	{
		$catQuery = "INSERT INTO Books VALUES(last_insert_id(), '$_POST[bookTypeID]', '$_POST[title]', '$_POST[author]', '$_POST[isbn]', '$_POST[assignedCourse]', '$_POST[conditionID]')";
	}

	if($category=="Electronics")
	{
		$catQuery = "INSERT INTO Electronics VALUES(last_insert_id(), '$_POST[electronicsTypeID]', '$_POST[make]', '$_POST[model]', '$_POST[size]')";
	}

	if($category=="Furniture")
	{
		$catQuery = "INSERT INTO Bikes VALUES(last_insert_id(), '$_POST[furnitureTypeID]', '$_POST[conditionID]')";
	}

	if($category=="Meetups")
	{
		$catQuery = "INSERT INTO Meetups VALUES(last_insert_id(), '$_POST[meetupTypeID]', '$_POST[location]', '$_POST[date]', '$_POST[time]')";
	}

	if($category=="Miscellaneous")
	{
		$catQuery = "INSERT INTO Miscellaneous VALUES(last_insert_id())";
	}

	if($category=="Rides")
	{
		$catQuery = "INSERT INTO Rides VALUES(last_insert_id(), '$_POST[leavingFrom]', '$_POST[goingTo]', '$_POST[departureDate]', '$_POST[departureTime]', '$_POST[returnDate]', '$_POST[returnTime]')";
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

	// begin insert transaction
	mysql_query("SET AUTOCOMMIT=0");
	mysql_query("START TRANSACTION");

	// insert into Listings
	$query=$listQuery;
	$ins=mysql_query($query);
	if(!$ins)
	{
		$message = 'Insert into Listings table failed: ' . mysql_error() . '\n';
    $message .= 'Whole statement: ' . $query;
    die($message);
	}

	// insert into [category]
	$query=$catQuery;
	$ins=mysql_query($query);
	if(!$ins)
	{
		$message = "Insert into $category failed: " . mysql_error() . '\n';
    $message .= 'Whole statement: ' . $query;
    die($message);
	}

	// commit changes
	$query="COMMIT";
	$ins=mysql_query($query);
	if(!$ins)
	{
		$message = 'Transaction failed: ' . mysql_error() . '\n';
    $message .= 'Whole statement: ' . $query;
    die($message);
	}
	header('Location: home.html');
?>
