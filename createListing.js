$(document).click(function(){
	$('#newListing').click(function(e){
		console.log('test');
		var xmlHttp = getXMLHttp();
		xmlHttp.open("GET", "phpAPI.php", true);
		xmlHttp.send('$_GET[userID]', '$_GET[title]', '$_GET[category]',
			'$_GET[description]', '$_GET[itemName]')
	});
});