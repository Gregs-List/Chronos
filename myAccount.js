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

function editRedirect(e){
	var text = e.target.value;
	window.location = "editListing.html?" + text;
}


window.onload=function(){
document.write('<link rel="stylesheet" type="text/css" href="myAccount.css">'); };

$(document).ready( function() {
	getPhone();
	$('#editButton').click(editRedirect);

});
