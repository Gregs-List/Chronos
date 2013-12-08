



//Use this array to hold objects which contain the following:
//location, description, date and an actual Image element.

var links = [];

function listingLink(){
	var title;
	var price;
	var description;
	var id;
}

function radioFilter(e){

		var data = window.location.search;

	
		if (data.substring(0, 1) == '?') {
	    	data = data.substring(1);
	  	}	

		var request = new XMLHttpRequest();
		var json;
		links = [];
		var cat = e.target.value;
		var url = "catTermSearch.php?search="+data+"&category="+cat;
		request.open("GET", url, true);

		request.onreadystatechange = function(e)
		{
			if(request.readyState == 4){
				json = JSON.parse(request.responseText);
				console.log(json);
				for(var x = 0; x < json.length; x++){
					var link = new listingLink();
					link.title = json[x].title;
					link.price = json[x].price;
					link.description = json[x].description;
					link.id = json[x].listingID;
					links.push(link);
				}
			}
			console.log(links);
			addRows();
		}
		request.send();
}

function categoryFilter(data){
		var request = new XMLHttpRequest();
		var json;
		links = [];
		var url = "categorySearch.php?category="+data;
		request.open("GET", url, true);

		request.onreadystatechange = function(e)
		{
			if(request.readyState == 4){
				json = JSON.parse(request.responseText);
				console.log(json);
				for(var x = 0; x < json.length; x++){
					var link = new listingLink();
					link.title = json[x].title;
					link.price = json[x].price;
					link.description = json[x].description;
					link.id = json[x].listingID;
					links.push(link);
				}
			}
			console.log(links);
			addRows();
		}
		request.send();
}

function textResults(data){
		var request = new XMLHttpRequest();
		var json;
		links = [];
		var url = "search.php?search="+data;
		request.open("GET", url, true);

		request.onreadystatechange = function(e)
		{
			if(request.readyState == 4){
				json = JSON.parse(request.responseText);
				console.log(json);
				for(var x = 0; x < json.length; x++){
					var link = new listingLink();
					link.title = json[x].title;
					link.price = json[x].price;
					link.description = json[x].description;
					link.id = json[x].listingID;
					links.push(link);
				}
			}
			console.log(links);
			addRows();
		}
		request.send();
}

function addRows(){
	$('#listingTable').empty();
	$('#listingTable').append('<tr><td><h3>Title</h3></td><td><h3>Price</h3></td><td><h3>Description</h3></td></tr>');
	var len = links.length;
	for(var x = 0; x < len; x++){
		var row = '<tr><td><h4><a href="listing.html?'+links[x].id+'">'+links[x].title+'</a></td><td>'+links[x].price+'</td><td>'+links[x].description+'</h4></td></tr>';
		$('#listingTable').append(row);
	}
}




$(document).ready( function() {

	$('input:radio').change(radioFilter);

	var data = window.location.search;

	
	if (data.substring(0, 1) == '?') {
    	data = data.substring(1);
    	if(data == "Books" || data == "Furniture" || data == "Electronics" || data == "Bikes" || data == "Miscellaneous"){
    		$('#filterButtons').css('display','none');
    		categoryFilter(data);
    	}
    	else{
    		textResults(data);
    	}
    	
  	}	
	
});






