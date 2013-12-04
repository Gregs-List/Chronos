<!DOCTYPE html>
<html lang="en">
	<head>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" ></script>
		<script src="categoryBar.js" ></script>
		<script src="searchBar.js" ></script>
		<!--<script type="text/javascript" src="myAccount.js"></script>-->
		<link rel="stylesheet" type="text/css" href="myAccount.css">
		<link rel="stylesheet" type="text/css" href="navBar.css">
		<link rel="stylesheet" type="text/css" href="categoryBar.css">
		<title>My Account</title>
	</head>

	<body>
	    <img src = "site_banner.png" alt= "Greg's List" class="logo"/>
		<nav id = "navBar"> 
			<a class = "nav_item" href="home.html">Home</a>
			<a class = "nav_item" href="myAccount.php">My Account</a>
			<a class = "nav_item" href="createListing.html">Create Listing</a>
			<a class = "nav_item" href="index.html">Logout</a>

			<div id="searchBar" name="searchBar">
					<input type="text" value="search" id="requestText">
					<input type="submit" value="Search" id="searchButton">
			</div>
		</nav>

		<nav id = "categoryBar">
			<h4>Categories</h4>
			<a class = "category">Books</a>
			<a class = "category">Furniture</a>
			<a class = "category">Electronics</a>
			<a class = "category">Bikes</a>
		</nav>

		<div id="contactInfo">
			<h3>Contact Information</h3>
			<label  for="email">Email Address:</label>
			<?php include 'email.php';?>
			<form name="phone" action="updateUserInfo.php" method="post">
				<label  for="phoneNumber">Phone Number:</label>
				<input type="text" name="phoneNumber" id="phoneNumber" pattern = "[^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$" title="Enter a valid phone number" required> 
				<input type="submit" value="Save" id="savePhone">
			</form>
		</div>

		<div id="myListings">
			<h3>My Listings</h3>
			<?php include 'getListing.php';?>
		</div>

	</body>
</html>
