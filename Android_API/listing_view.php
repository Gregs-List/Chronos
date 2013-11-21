<?php
$con = mysql_connect("localhost", "listAdmin", "hermes");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());

		$category = $_GET['category'];
		$listingID = $_ListingID['listingID'];



		if ($category == "Bikes") {
			$query_bikes = "Select * from Bikes where listingID = $listingID";
		    $result = mysql_query($query_bikes);
            $info = array();
            while ($row = mysql_fetch_assoc($result)){
            	$bikeTypeID = $row['bikeTypeID'];
            	$make = $row['make'];
            	$model = $row['model'];
			}

			echo $bikeTypeID;
			echo $make;
			echo $model;

			$query_bikeType = "Select * from BikeType where bikeTypeID = $bikeTypeID";
			$result2 = mysql_query($query_bikeType);
			while ($row = mysql_fetch_assoc($result2)){
				$bikeType = $row['bikeType'];
			}

			$info = array("make"=>$make, "model"=>$model, "type"=>$bikeType);
			$json = json_encode($info);
			echo $json;
		}


		if ($category == "Books") {
			$query_books = "Select * from Books where listingID = $listingID";
		    $result3 = mysql_query($query_books);
            $info = array();
            while ($row = mysql_fetch_assoc($result)){
            	$bookTypeID = $row['bookTypeID'];
            	$title = $row['title'];
            	$author = $row['author'];
            	$isbn = $row['isbn'];
            	$assignedCourse = $row['assignedCourse'];
            	$conditionID= $row['conditionID'];
			}

			echo $bookTypeID;
			echo $title;
			echo $author;
			echo $isbn;
			echo $assignedCourse;
			echo $conditionID;

			$query_bookType = "Select * from BookType where bookTypeID = $bookTypeID";
			$result3 = mysql_query($query_bookType);
			while ($row = mysql_fetch_assoc($result3)){
				$bookType = $row['bookType'];
			}

			$query_bookCondition = "Select * from ConditionLookup where conditionID = $conditionID";
			$result4 = mysql_query($query_bookCondition);
			while ($row = mysql_fetch_assoc($result4)){
				$bookCondition = $row['itemCondition'];
			}

			$info = array("title"=>$title, "author"=>$author, "isbn"=>$isbn, "assignedCourse"=>$assignedCourse, "type"=>$bookType, "condition"=>$bookCondition);
			$json = json_encode($info);
			echo $json;
		}

?>