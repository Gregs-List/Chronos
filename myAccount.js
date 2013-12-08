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

$(document).ready( function() {
	getPhone();
	$('#editButton').click(editRedirect);

});

window.onload=function(){
$('#editButton').css("background","url('edit.png') no-repeat");
};
