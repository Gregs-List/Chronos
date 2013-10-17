<?PHP

/* Get some number of most recent listings, 
order by most to least recent, 
encode to JSON and print */
function getRecentListings()	#incomplete
  {
    $con = mysql_connect("localhost", "listAdmin", "hermes");
    if (!$con)
    {
       die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("GregsList", $con)
       or die("Unable to select database:" . mysql_error());
	}

/* Get some number of recent listings by some user,
order by most to least recent,
encode to JSON and print */

function getUserListings()	#incomplete
  {
    $con = mysql_connect("localhost", "listAdmin", "hermes");
    if (!$con)
    {
       die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("GregsList", $con)
       or die("Unable to select database:" . mysql_error());
	}

/* Get some numbe of recent listings by category
order by most to least recent,
encode to JSON and print */

function getUserListings()	#incomplete
  {
    $con = mysql_connect("localhost", "listAdmin", "hermes");
    if (!$con)
    {
       die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("GregsList", $con)
       or die("Unable to select database:" . mysql_error());
	}



?>
