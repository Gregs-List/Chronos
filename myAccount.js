function getPhone(){


	var request = new XMLHttpRequest();
	var phoneNum;
	request.open("GET", 'account_phone.php', true);

	request.onreadystatechange = function(e)
	{
		if(request.readyState == 4){
			console.log(request.responseText)
			phoneNum = request.responseText;
			$('#phoneNumber').attr("placeholder", phoneNum);
		}
	}
	request.send();

}





$(document).ready( function() {
	getPhone();
	
});
