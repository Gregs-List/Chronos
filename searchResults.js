



//Use this array to hold objects which contain the following:
//location, description, date and an actual Image element.

var links = [];

function listingLink(){
	var title;
	var price;
	var description;
}

function radioFilter(e){
		var request = new XMLHttpRequest();
		var json;
		links = [];
		var data = e.target.value;
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
	$('#listingTable').append('<tr><td>Title</td><td>Price</td><td>Description</td></tr>');
	var len = links.length;
	for(var x = 0; x < len; x++){
		var row = '<tr><td>'+links[x].title+'</td><td>'+links[x].price+'</td><td>'+links[x].description+'</td></tr>';
		$('#listingTable').append(row);
	}
}




$(document).ready( function() {

	$('input:radio').change(radioFilter);

	var data = window.location.search;
	if (data.substring(0, 1) == '?') {
    	data = data.substring(1);
    	if(data == "Books" || data == "Furniture" || data == "Electronics" || data == "Bikes"){
    		categoryFilter(data);
    	}
    	else{
    		textResults(data);
    	}
    	
  	}	
	
});





