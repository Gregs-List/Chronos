$(document).ready(function(){
	$('#signUp').click(function(e){

		var email = $('#newEmail').val();
		var len = email.length;
		var smu = "tcu.edu" //just a holder string the in inheiently wrong
		if(len > 6)
			smu = email.substring(len-7,len);

		var p1 = $('#newPassword').val();
		var p2 = $('#reEnterPassword').val();


		if(smu != 'smu.edu'){
			e.preventDefault();
			alert("You must use a valid SMU email address.");
		}

		
		else if (p1 != p2){
			e.preventDefault();
			alert("Your passwords don't match");
		}

		else{
			alert("Sign Up Complete. You may now login.");
		}

	});
});
