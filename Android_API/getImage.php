<?php

$con = mysql_connect("localhost", "listAdmin", "hermes");
    if (!$con)
    {
        die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("GregsList", $con) 
        or die("Unable to select database:" . mysql_error());

$listingID = $_GET['id'];

$query = "Select * from Photos where listingID='$listingID'";
$result = mysql_query($query);
while ($row = mysql_fetch_assoc($result)){
	$url = $row['photoURL'];
	}
echo $url;
?>