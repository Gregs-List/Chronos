<?php
$con = mysql_connect("localhost", "listAdmin", "hermes");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());

$category = $_GET['category'];
$listingID = $_GET['listingID'];

if ($category == "Bikes") {
			$query_bikes = "Select * from Bikes where listingID = $listingID";
		    $result = mysql_query($query_bikes);
            $info = array();
            while ($row = mysql_fetch_assoc($result)){
            	$bikeTypeID = $row['bikeTypeID'];
            	$make = $row['make'];
            	$model = $row['model'];
			}

			$query_bikeType = "Select * from BikeType where bikeTypeID = $bikeTypeID";
			$result2 = mysql_query($query_bikeType);
			while ($row = mysql_fetch_assoc($result2)){
				$bikeType = $row['bikeType'];
			}

			$info = array("make"=>$make, "model"=>$model, "type"=>$bikeType, "category"=>$category);
			$json = json_encode($info);
			echo $json;
		}


else if ($category == "Books") {
			$query_books = "Select * from Books where listingID = $listingID";
		    $result3 = mysql_query($query_books);
            $info = array();
            while ($row = mysql_fetch_assoc($result3)){
            	$bookTypeID = $row['bookTypeID'];
            	$title = $row['title'];
            	$author = $row['author'];
            	$isbn = $row['isbn'];
            	$assignedCourse = $row['assignedCourse'];
            	$conditionID= $row['conditionID'];
			}

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

			$info = array("title"=>$title, "author"=>$author, "isbn"=>$isbn, "assignedCourse"=>$assignedCourse, "type"=>$bookType, "condition"=>$bookCondition, "category"=>$category);
			$json = json_encode($info);
			echo $json;
		}

else if ($category == "Electronics") {
			$query_electronics = "Select * from Electronics where listingID = $listingID";
		    $result5 = mysql_query($query_electronics);
            $info = array();
            while ($row = mysql_fetch_assoc($result5)){
            	$electronicsTypeID = $row['electronicsTypeID'];
            	$make = $row['make'];
            	$model = $row['model'];
            	$size = $row['size'];
			}

			$query_electronicsType = "Select * from ElectronicsType where electronicsTypeID = $electronicsTypeID";
			$result6 = mysql_query($query_electronicsType);
			while ($row = mysql_fetch_assoc($result6)){
				$electronicsType = $row['electronicsType'];
			}


			$info = array("make"=>$make,"model"=>$model,"size"=>$size,"type"=>$electronicsType, "category"=>$category);
			$json = json_encode($info);
			echo $json;
		}
		

else if ($category == "Furniture") {
			$query_furniture = "Select * from Furniture where listingID = $listingID";
		    $result6 = mysql_query($query_furniture);
            $info = array();
            while ($row = mysql_fetch_assoc($result6)){
            	$furnitureTypeID = $row['furnitureTypeID'];
            	$conditionID = $row['conditionID'];
			}

			$query_furnitureType = "Select * from FurnitureType where furnitureTypeID = $furnitureTypeID";
			$result7 = mysql_query($query_furnitureType);
			while ($row = mysql_fetch_assoc($result7)){
				$furnitureType = $row['furnitureType'];
			}

			$query_furnitureCondition = "Select * from ConditionLookup where conditionID = $conditionID";
			$result8 = mysql_query($query_bookCondition);
			while ($row = mysql_fetch_assoc($result8)){
				$furnitureCondition = $row['itemCondition'];
			}


			$info = array("type"=>$furnitureType,"condition"=>$furnitureCondition, "category"=>$category);
			$json = json_encode($info);
			echo $json;
		}

else if ($category == "Meetups") {
			$query_meetups = "Select * from Meetups where listingID = $listingID";
		    $result9 = mysql_query($query_meetups);
            $info = array();
            while ($row = mysql_fetch_assoc($result9)){
            	$meetupTypeID = $row['meetupTypeID'];
            	$location = $row['location'];
            	$date = $row['date'];
            	$time = $row['time'];
			}


			$query_meetupType = "Select * from MeetupType where meetupTypeID = $meetupTypeID";
			$result10 = mysql_query($query_meetupType);
			while ($row = mysql_fetch_assoc($result10)){
				$meetupType= $row['meetupType'];
			}



			$info = array("type"=>$meetupType,"location"=>$location, "date"=>$date, "time"=>$time, "category"=>$category);
			$json = json_encode($info);
			echo $json;
		}

else if ($category == "Miscellaneous") {
			$query_misc = "Select * from Miscellaneous where listingID = $listingID";
		    $result11 = mysql_query($query_misc);
            $info = array();
            while ($row = mysql_fetch_assoc($result11)){
            	$itemName= $row['itemName'];
			}


			$info = array("name"=>$itemName);
			$json = json_encode($info);
			echo $json;
		}

else if ($category == "Rides") {
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


			$info = array("leavingFrom"=>$leavingFrom, "goingTo"=>$goingTo, "departureTime"=>$departureTime, "departureDate"=>$departureDate, "returnTime"=>$returnTime, "returnDate"=>$returnDate, "category"=>$category);
			$json = json_encode($info);
			echo $json;
		}

?>