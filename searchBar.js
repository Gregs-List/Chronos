function sendText(e)
{
  var text = $('#requestText').val();
  window.location = "searchResults.html?" + text;
}


$(document).ready( function() {
	$('#searchButton').click(sendText);
});