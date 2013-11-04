<!DOCTYPE html>
<html lang="en">
	<head>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" ></script>
		<!--<script type="text/javascript" src="myAccount.js"></script>-->
		<link rel="stylesheet" type="text/css" href="navBar.css">
		<link rel="stylesheet" type="text/css" href="myAccount.css">
		<title>My Account</title>
	</head>

	<body>
		<nav id = "navBar"> 
			<a href="index.html">Logout</a>
			<a href="myAccount.php">My Account</a>
			<a href="createListing.html">Create Listing</a>
			<a href="home.html">Home</a>
		</nav>

		<nav id="accountNav">
			<h4>My Account Links</h4>
			<a href="#contactInfo">Contact Info</a>
			<a href="#myListings">My Listings</a>
			<a href="#watchedListings">Watched Listings</a>
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

		<div id="watchedListings">
			<h3>Watched Listing</h3>
			<h4> Not implemented yet</h4>
		</div>





	</body>
</html>