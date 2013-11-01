<?php
	$con = mysql_connect("localhost", "gregslist", "greg");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
function register(){
	mysql_select_db("GregsList", $con)
		or die("Unable to select database:" . mysql_error());
	$query = "SELECT email FROM Users WHERE email='$_GET[email]'";
	$result = mysql_query($query);
	if (mysql_num_rows($result) > 0){
?>
		<script type="text/javascript">
			alert("The email address <?php echo $_GET['email'] ?> is already in use");
			history.back();
		</script>
<?php
	}
	else {
	$query = "INSERT INTO Users(userID, email, pword, fullName, 			location, phoneNumber) VALUES(NULL,'$_GET[email]', 
		'$_GET[pword]','$_GET[fullname]',
		'$_GET[location]','$_GET[phoneNumber]')";
	mysql_query($query);	
	header('Location: index.html');
	}
}
?>
