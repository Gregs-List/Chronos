var request = new XMLHttpRequest();
var listings = [];
var json;
//var url = "getListing.php";
request.open("GET", 'getListing.php', true);

request.onreadystatechange = function(e)
{
	if(request.readyState == 4 || request.status== 200){
		console.log(request.responseText);
		json = JSON.parse(request.responseText);
		
		for(var x = 0; x < json.length; x++){
			var list = new listingInfo();
			list.category = json[x].category;
			list.date = json[x].dateListed;
			list.description = json[x].description;
			list.id = json[x].listingID;
			list.title = json[x].title;
			list.userID = json[x].userID;
			listings.push(list);
		}	
	}
	console.log(listings);
}
request.send();

function listingInfo(){
	var category;
	var date;
	var description;
	var id;
	var title;
	var userID;
};

$(document).ready( function() {
	var myListings = $('#listings');
	for(var x =0; x < listings.length; x++){
		var newListing = $("<li></li>");
		newListing.html(listings[x].title + " " + listings[x].date);
		myListings.append(newListing);
	}
});
