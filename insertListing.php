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
	$title = addslashes($_POST['title']);
	$category = $_POST['category'];
	$description = addslashes($_POST['description']);
	$price = $_POST['price'];

	$last = mysql_query("SELECT photoID FROM Photos ORDER BY photoID DESC LIMIT 1"); 
	if(!$last){
		echo 'SELECT photoID failed. <br> ';}
	$lastPhoto = mysql_fetch_array($last, MYSQL_ASSOC); 		
	$lastPhotoID = $lastPhoto['photoID'];
	$photoID = $lastPhotoID + 1;
	echo "Last photo ID: " . $lastPhotoID . "<br>";
	echo "New photo ID: " . $photoID . "<br>";

	$listQuery = "INSERT INTO Listings VALUES(NULL, '$userID', '$title',NULL, '$category', '$price', '$description')";


	if($category=="Bikes")
	{
		$make = addslashes($_POST['bMake']);
		$model = addslashes($_POST['bModel']);
		$catQuery = "INSERT INTO Bikes VALUES(last_insert_id(), '$_POST[bikeTypeID]', '$make', '$model')";
	}
	
	if($category=="Books")
	{
		$title = addslashes($_POST['bookTitle']);
		$author = addslashes($_POST['author']);
		$course = addslashes($_POST['assignedCourse']);
		$catQuery = "INSERT INTO Books VALUES(last_insert_id(), '$_POST[bookTypeID]', '$title', '$author', '$_POST[isbn]', '$course', '$_POST[bookCondition]')";
	}

	if($category=="Electronics")
	{
		$make = addslashes($_POST['eMake']);
		$model = addslashes($_POST['eModel']);
		$catQuery = "INSERT INTO Electronics VALUES(last_insert_id(), '$_POST[electronicsTypeID]', '$make', '$model', NULL)";
	}

	if($category=="Furniture")
	{
		$catQuery = "INSERT INTO Furniture VALUES(last_insert_id(), '$_POST[furnitureTypeID]', '$_POST[furnitureCondition]')";
	}

	if($category=="Meetups")
	{
		$location = addslashes($_POST['location']);
		$catQuery = "INSERT INTO Meetups VALUES(last_insert_id(), '$_POST[meetupTypeID]', '$location', '$_POST[date]', '$_POST[time]')";
	}

	if($category=="Miscellaneous")
	{
		$catQuery = "INSERT INTO Miscellaneous VALUES(last_insert_id(),NULL)";
	}

	if($category=="Rides")
	{
		$catQuery = "INSERT INTO Rides VALUES(last_insert_id(), '$_POST[leavingFrom]', '$_POST[goingTo]', '$_POST[departureDate]', '$_POST[departureTime]', '$_POST[returnDate]', '$_POST[returnTime]')";
	}


	$allowedExts = array("gif", "jpeg", "jpg", "png");
	$temp = explode(".", $_FILES["photos"]["name"]);
	$extension = end($temp);
	$photoUrl = $photoID . "." . $extension;
	$photoInserted=false;
	if(empty($_FILES["photos"])){echo 'No photo to upload.<br> ';}
	
	elseif ((($_FILES["photos"]["type"] == "image/gif")
	|| ($_FILES["photos"]["type"] == "image/jpeg")
	|| ($_FILES["photos"]["type"] == "image/jpg")
	|| ($_FILES["photos"]["type"] == "image/pjpeg")
	|| ($_FILES["photos"]["type"] == "image/x-png")
	|| ($_FILES["photos"]["type"] == "image/png"))
	&& ($_FILES["photos"]["size"] < 5000000)
	&& in_array($extension, $allowedExts))
  {	
	 	echo 'Image is of allowed type and size.<br> ';
		if ($_FILES["photos"]["error"] > 0)
		  {
		  echo "Return Code: " . $_FILES["photos"]["error"] . "<br> ";
		  }
		else
		{
			echo $photoUrl . "<br>";
			$moved = move_uploaded_file($_FILES["photos"]["tmp_name"],
			"User_Photos/" . $photoUrl);
			$fileQuery = "INSERT INTO Photos VALUES (last_insert_id(), '$photoID', '$photoUrl')";
		}
			if($moved) {echo "Stored in: " . "User_Photos/" . $photoUrl . "<br> ";}				 
		  
	}

else
  {
  echo "Invalid file<br>\n";
  }


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

	// insert into Photos
	if($moved)
	{	
		$ins=mysql_query($fileQuery);
		if(!$ins)
		{
			$message = 'Insert into Photos table failed: ' . mysql_error() . '<br>';
			$message .= 'Whole statement: ' . $query;
		}
	}

	// commit changes
	$query="COMMIT";
	$ins=mysql_query($query);
	if(!$ins)
	{
		$message = 'Transaction failed: ' . mysql_error() . '<br>';
    die($message);
	}
//	else{header('Location: home.html');}
?>
