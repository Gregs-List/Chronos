$(document).click(function(){
	$('#signUp').click(function(e){
		var p1 = $('#newPassword').val();
		var p2 = $('#reEnterPassword').val();
		if (p1 != p2){
			e.preventDefault();
			alert("Your passwords don't match");
		}
		var email = $('#newEmail').val();

		var smu = email.substring(len-7,len);
		if(smu != 'smu.edu'){
			e.preventDefault();
			alert("You must use a valid SMU email address.");
		}
	});
});
