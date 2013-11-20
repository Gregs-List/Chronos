<?php
$con = mysql_connect("localhost", "listAdmin", "hermes");
    if (!$con)
    {
        die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("GregsList", $con) 
        or die("Unable to select database:" . mysql_error());

	$id = $_GET['userID'];

	$query = "Select * from Users where userID = '$id'";

	$result = mysql_query($query);


	while ($row = mysql_fetch_assoc($result)){
        $name = $row['fullName'];
        $email = $row['email'];
        $location = $row['location'];
        $phoneNumber = $row['phoneNumber'];
        $array = array("name"=>$name,"email"=>$email,"location"=>$location,"phoneNumber"=>$phoneNumber);
    	$json = json_encode($array);
    	echo $json;
    }

?>