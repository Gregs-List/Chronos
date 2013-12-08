function getPhone(){


	var request = new XMLHttpRequest();
	var phoneNum;
	request.open("GET", 'account_phone.php', true);

	request.onreadystatechange = function(e)
	{
		if(request.readyState == 4){
			console.log(request.responseText);
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


function deleteListing(e){
	var text = e.target.value;
	var request = new XMLHttpRequest();
	request.open("GET", 'deleteListing.php?listingID='+text, true);
	request.onreadystatechange = function(e)
	{
		if(request.readyState == 4){
		console.log(request.responseText);
		window.location="myAccount.php";
		}
	}
	request.send();
}


$(document).ready( function() {
	getPhone();
	$('.editButton').click(editRedirect);
	$('.deleteButton').click(deleteListing);

});
/*
window.onload=function(){
$('#editButton').css("background","url('edit.png') no-repeat");
$('#editButton').css("cursor","pointer");
$('#editButton').css("float","right");
$('#editButton:focus').css("outline","none");
$('#editButton').css("margin-top","30px");
$('#editButton').css("border","1px white solid");
};*/
