<?php
$con = mysql_connect("localhost", "listAdmin", "hermes");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());

//$category = $_GET['category'];
$category = "Bikes";
echo $category;

//$listingID = $_ListingID['listingID'];
$listingID = 58;
echo $listingID;



if ($category == "Bikes") {
	echo "This item is a bike";
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
	echo "This item is a book";
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

if ($category == "Electronics") {
	echo "This item is an electronic";
			$query_electronics = "Select * from Electronics where listingID = $listingID";
		    $result5 = mysql_query($query_electronics);
            $info = array();
            while ($row = mysql_fetch_assoc($result5)){
            	$electronicsTypeID = $row['electronicsTypeID'];
            	$make = $row['make'];
            	$model = $row['model'];
            	$size = $row['size'];
			}

			echo $electronicsTypeID;
			echo $make;
			echo $model;
			echo $size;

			$query_electronicsType = "Select * from ElectronicsType where electronicsTypeID = $electronicsTypeID";
			$result6 = mysql_query($query_electronics);
			while ($row = mysql_fetch_assoc($result6)){
				$electronicsType = $row['electronicsType'];
			}


			$info = array("make"=>$make,"model"=>$model,"size"=>$size,"type"=>$electronicsType);
			$json = json_encode($info);
			echo $json;
		}

if ($category == "Furniture") {
	echo "This item is furniture";
			$query_furniture = "Select * from Furniture where listingID = $listingID";
		    $result6 = mysql_query($query_furniture);
            $info = array();
            while ($row = mysql_fetch_assoc($result6)){
            	$furnitureTypeID = $row['furnitureTypeID'];
            	$conditionID = $row['conditionID'];
			}

			echo $furnitureTypeID;
			echo $conditionID;

			$query_furnitureType = "Select * from FurnitureType where furnitureTypeID = $furnitureTypeID";
			$result7 = mysql_query($query_furnitureType);
			while ($row = mysql_fetch_assoc($result7)){
				$furnitureType = $row['furnitureType'];
			}

			echo $furnitureType;

			$query_furnitureCondition = "Select * from ConditionLookup where conditionID = $conditionID";
			$result8 = mysql_query($query_bookCondition);
			while ($row = mysql_fetch_assoc($result8)){
				$furnitureCondition = $row['itemCondition'];
			}


			echo $furnitureCondition

			$info = array("type"=>$furnitureType,"condition"=>$furnitureCondition);
			$json = json_encode($info);
			echo $json;
		}

if ($category == "Meetups") {
	echo "This item is a meetup";
			$query_meetups = "Select * from Meetups where listingID = $listingID";
		    $result9 = mysql_query($query_meetups);
            $info = array();
            while ($row = mysql_fetch_assoc($result9)){
            	$meetupTypeID = $row['meetupTypeID'];
            	$location = $row['location'];
            	$date = $row['date'];
            	$time = $row['time'];
			}

			echo $meetupTypeID;
			echo $location;
			echo $date;
			echo $time;

			$query_meetupType = "Select * from MeetupType where meetupTypeID = $meetupTypeID";
			$result10 = mysql_query($query_meetupType);
			while ($row = mysql_fetch_assoc($result10)){
				$meetupType= $row['meetupType'];
			}

			echo $meetupType;

			$info = array("type"=>$meetupType,"location"=>$location, "date"=>$date, "time"=>$time);
			$json = json_encode($info);
			echo $json;
		}

if ($category == "Miscellaneous") {
	echo "This item is miscellaneous";
			$query_misc = "Select * from Miscellaneous where listingID = $listingID";
		    $result11 = mysql_query($query_misc);
            $info = array();
            while ($row = mysql_fetch_assoc($result11)){
            	$itemName= $row['itemName'];
			}

			echo $itemName;

			$info = array("name"=>$itemName);
			$json = json_encode($info);
			echo $json;
		}

if ($category == "Rides") {
	echo "This item is a ride";
			$query_rides = "Select * from Rides where listingID = $listingID";
		    $result12 = mysql_query($query_rides);
            $info = array();
            while ($row = mysql_fetch_assoc($result12)){
            	$leavingFrom = $row['leavingFrom'];
            	$goingTo = $row['goingTo'];
            	$departureDate = $row['departureDate'];
            	$departureTime = $row['departureTime'];
            	$returnDate = $row['returnDate'];
            	$returnTime = $row['returnTime'];
            				}

			echo $leavingFrom;
			echo $goingTo;
			echo $departureTime;
			echo $departureDate;
			echo $returnTime;
			echo $returnDate;


			$info = array("leavingFrom"=>$leavingFrom, "goingTo"=>$goingTo, "departureTime"=>$departureTime, "departureDate"=>$departureDate, "returnTime"=>$returnTime, "returnDate"=>$returnDate);
			$json = json_encode($info);
			echo $json;
		}


?>