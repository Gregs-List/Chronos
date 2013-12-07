<?php
	session_start();
	$con = mysql_connect("localhost", "listAdmin", "hermes");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());
	$query = "SELECT email FROM Users WHERE email='$_POST[email]'";
	echo $query;
	$result = mysql_query($query);
	if (mysql_num_rows($result) > 0){
?>
		<script type="text/javascript">
			alert("The email address <?php echo $_POST['email'] ?> is already in use");
			history.back();
		</script>
<?php
	}
	else {
		$query = "INSERT INTO Users(userID, email, pword, fullName) 
			VALUES(NULL,'$_POST[email]','$_POST[password]','$_POST[fname]')";
		$ins=mysql_query($query);	
		if(!ins)
		{
			$message = "User registration failed: " . mysql_error() . "<br>";
		  $message .= 'Insert statement: ' . $query . "<br>";
		}
		else{header('Location: index.html');}
	}
?>
