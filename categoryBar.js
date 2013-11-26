function sendData(e)
{
  var category = e.target.innerHTML;
  window.location = "searchResults.html?" + category;
}


$(document).ready( function() {
	var searchLinks = $('#categoryBar a');
	for (var x = 0; x < searchLinks.length; x++){
		searchLinks.eq(x).click(sendData);
	}
});