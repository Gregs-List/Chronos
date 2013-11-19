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
        $listingID = $row['listingID'];
        $userListingID = $row['userID'];
        $title = $row['title'];
        $dateListed = $row['dateListed'];
        $category = $row['category'];
        $price = $row['price'];
        $description = $row['description'];
        $listing = array("listingID" => $listingID, "userListingID" =>$userListingID, "title" => $title, "dateListed" => $dateListed, "category" => $category, "price" => $price, "description" => $description);
        array_push($info, $listing);
        $counter++;
    }
}
    $full_array= array("listings"=>$info);

    $json = json_encode($full_array);
    echo $json;
    
    mysql_close($con);
    ?>