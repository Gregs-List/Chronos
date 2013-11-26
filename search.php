<?php
	session_start();
	$con = mysql_connect("localhost", "listAdmin", "hermes");
	if(!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());


	$search = $_GET['search'];
	
	#if(!empty($_POST['category']))
	#$category = $_POST['category'];

	if(isset($_GET['category']) && isset($_GET['price']))
	{
		$category = $_GET['category']; 
		$price = $_GET['price'];
		$query = "SELECT title, description, price FROM Listings WHERE category='$category' and title or description LIKE '%$search%' HAVING price <= '$price' ORDER BY dateListed DESC";
	}
	elseif(isset($_GET['category']))
	{
		$category = $_GET['category'];

		$query = "SELECT title, description, price FROM Listings WHERE category='$category' and title or description LIKE '%$search%' ORDER BY dateListed DESC";
	}
	elseif(isset($_GET['price']))
	{
		$price = $_GET['price'];
		
		$query = "SELECT title, description, price FROM Listings WHERE price <= '$price' and title or description LIKE '%$search%' ORDER BY dateListed DESC";		
	}
	elseif(isset($_GET['search']))
	{
		$query = "SELECT title, description, price FROM Listings WHERE title or description LIKE '%$search%' ORDER BY dateListed DESC";
	}
	else
	{
		$query = "SELECT title, description, price FROM Listings ORDER BY dateListed DESC";
	}	

	$result = mysql_query($query);
		
	#converts to json
	$rows = array();
	while($r = mysql_fetch_assoc($result)) 
	{
    		$rows[] = $r;
	}

	echo json_encode($rows);
	#Header('Location: searchResults.html');
?>
