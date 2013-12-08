<!DOCTYPE html>
<html lang="en">
<?php 
	session_start();
	if ($_SESSION['userID'] == NULL) {
    	header('Location:index.html');}
?>
	<head>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" ></script>
		<script src="home.js" ></script>
		<script src="categoryBar.js" ></script>
		<script src="searchBar.js" ></script>
		<link rel="stylesheet" type="text/css" href="home.css">
		<title>Greg's List Home</title>
	</head>

	<body>
		<img src = "header.png" alt= "Greg's List" class="logo"/>
		<nav id = "navBar"> 
				<a class ="nav_item" href="home.php">Home</a>
				<a class ="nav_item" href="myAccount.php">My Account</a>
				<a class ="nav_item" href="createListing.html">Create Listing</a>
				<a class ="nav_item" href="logout.php">Logout</a>
				<span id = "search">
					<input type="text" placeholder="search" id="requestText">
					<input type="submit" value="" id="searchButton">
					</span>
					
		</nav>
		
		<nav id = "categoryBar">
				<h4><a class ="category">Books</a>
				<a class ="category">Furniture</a>
				<a class ="category">Electronics</a>
				<a class ="category">Bikes</a>
				<a class = "category">Miscellaneous</a></h4>
		</nav>

		<div id="photoBooth">
		    <div id="slideShow">
		      <div class="photoHolder">
		        <img class="thumbnail topPhoto" src="stupid_morgan.jpg" width="518px" height="389px" alt="Gallery Image"/>
		      </div>
		      <img class="moreIndicator rot90" src="arrow1.png" alt="More Content"/>   
		      <div id="nav">
		        <img id="prevPhoto" class='rot180' src="right.png" alt="Previous Photo"/>
		        <img id="nextPhoto" src="right.png" alt="Next Photo"/>
		      </div>
		      <div class="details">
		        <p class="title">Title: Title</p>
		        <p class="price">Price: Price</p>
		        <p class="description">Description: Description</p>
		        
		      </div>
		    </div>
		</div>
	</body>
</html>

