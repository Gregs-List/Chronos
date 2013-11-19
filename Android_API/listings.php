<?php

    $con = mysql_connect("localhost", "listAdmin", "hermes");
    if (!$con)
    {
        die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("GregsList", $con) 
        or die("Unable to select database:" . mysql_error());


    $query = "select * from Listings order by listingID DESC";
    $result = mysql_query($query);

    $info = array();
    $counter = 0;
    while ($row = mysql_fetch_assoc($result)){
        if ($counter < 10) {
        $title = $row['title'];
        array_push($info,$title);
        $category = $row['category'];
        array_push($info,$category);}
        $counter++;
    }

    foreach ($info as $value) {
        echo $value;
        echo "#";
    }
    
    mysql_close($con);
    ?>