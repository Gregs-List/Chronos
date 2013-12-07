<?php

    $con = mysql_connect("localhost", "listAdmin", "hermes");
    if (!$con)
    {
        die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("GregsList", $con) 
        or die("Unable to select database:" . mysql_error());

		//Revised fntion to check login credentials
		$user = mysql_real_escape_string($_POST['email']);
		$pass = mysql_real_escape_string($_POST['password']);
		$userCheck = mysql_query("SELECT * FROM Users WHERE email='$user' and pword='$pass'")
    or die (mysql_error());
		$userExists = mysql_fetch_array($userCheck, MYSQL_ASSOC);
		if(mysql_num_rows($userCheck)==1)
		{
			$userExists = mysql_fetch_array($userCheck, MYSQL_ASSOC);
			session_start();
			$_SESSION['userID'] = $userExists['userID'];
			header('Location: home.html');
		}
		else
		{
			echo "<META HTTP-EQUIV=REFRESH CONTENT='0; URL=index.html'><script type='text/javascript'>alert('Invalid username or password. If you do not have an account, register on the right.');</script>";
		}
		

    mysql_close($con);
?>
