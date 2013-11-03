$(document).click(function(){
	$('#signUp').click(function(e){
		var p1 = $('#newPassword').val();
		var p2 = $('#reEnterPassword').val();
		if (p1 != p2){
			e.preventDefault();
			alert("Your passwords don't match");
		}
	});
});