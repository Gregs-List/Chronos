<?PHP
	session_start();
# connect to MySQL server
	$con = mysql_connect("localhost", "listAdmin", "hermes");
  if (!$con)
  {
     die('Could not connect: ' . mysql_error());
  }
  mysql_select_db("GregsList", $con)
     or die("Unable to select database:" . mysql_error());

/*
# check/update fullName
	if(isset ($_POST['fullName'])
	{
		$fullName = $_POST['fullName'];
		$query = "UPDATE Users SET fullName='$fullName' WHERE userID='$userID'";
		mysql_query($query);
	}

#check/update location
	if(isset ($_POST['location'])
	{
		$location = $_POST['location'];
		$query = "UPDATE Users SET location='$location' WHERE userID='$userID'";
		mysql_query($query);
	}
*/

#check/update phoneNumber
		$phone = $_POST['phoneNumber'];
		$query = "UPDATE Users SET phoneNumber='$phone' WHERE userID='$_SESSION[userID]'";
		mysql_query($query);
	
/*
#check/update password
	if(isset ($_POST['password'])
	{
		$password = $_POST['password'];
		$query = "UPDATE Users SET pword='$password' WHERE userID='$userID'";
		mysql_query($query);
	}
*/
	mysql_close($con)
Header('Location:myAccount.html');
?>
