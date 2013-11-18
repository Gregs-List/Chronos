$(document).ready(function(){
	/*$('#newListing').click(function(e){
		var xmlHttp = getXMLHttp();
		xmlHttp.open("GET", "phpAPI.php", true);
		xmlHttp.send('$_GET[userID]', '$_GET[title]', '$_GET[category]',
			'$_GET[description]', '$_GET[itemName]')
	});*/

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


});


function hideFields(){
	$('#bookFields').css('display','none');
	$('#furnitureFields').css('display','none');
	$('#electronicFields').css('display','none');
	$('#bikeFields').css('display','none');
}