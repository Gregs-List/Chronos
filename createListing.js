$(document).ready(function(){


	$('#category').change(function(e){
		var text = $('#category :selected').text();
		if(text == 'Books'){
			hideFields();
			$('#bookFields').css('display','Block');
		}
		else if (text == 'Furniture'){
			hideFields();
			$('#furnitureFields').css('display','Block');
		}
		else if (text == 'Electronics'){
			hideFields();
			$('#electronicFields').css('display','Block');
		}
		else if (text == 'Bikes'){
			hideFields();
			$('#bikeFields').css('display','Block');
		}
		else if (text == 'Misellanious'){
			hideFields();
		}
	});

	$('#newListing').click(function(e){
		if($('#price').val() > 9999.99){
			e.preventDefault();
			alert("Price cannot exceed $9999.99");
		}
		else{
			alert("Listing created. You will be redirected to the home page.");
			window.location = "home.html";
		}
	});

});


function hideFields(){
	$('#bookFields').css('display','none');
	$('#furnitureFields').css('display','none');
	$('#electronicFields').css('display','none');
	$('#bikeFields').css('display','none');
}