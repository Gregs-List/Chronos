$(document).ready( function() {

	$('input:radio').change(radioFilter);

	var data = window.location.search;

	
	if (data.substring(0, 1) == '?') {
    	data = data.substring(1);
        fillListing(data);
  	}	
	
});

var listing = new listing();
var contact = new contact();
var book = new book();
var furniture = new furniture();
var electronic = new electronic();
var bike = new bike();

function listing(){
    var title;
    var price;
    var description;
    var category;
    var date;
}

function contact(){
    var name;
    var email;
    var phone;
}

function book(){
    var type;
    var condition;
    var title;
    var author;
    var isbn;
    var course;
}

function furniture(){
    var type;
    var condition;
}

function electronic(){
    var type;
    var make;
    var model;
}

function bike(){
    var type;
    var make;
    var model;
}

function fillListing(data){

    var request = new XMLHttpRequest();
    var json;
    var url = "viewFullListingJSON.php?listingID="+data;
    request.open("GET", url, true);

    request.onreadystatechange = function(e)
    {
        if(request.readyState == 4){
            json = JSON.parse(request.responseText);
            console.log(json);

            contact.name = json.fullName;
            contact.email = json.email;
            contact.phoneNumber = json.phoneNumber;

            listing.title = json.title;
            listing.date = json.dateListed;
            listing.price = json.price;
            listing.description = json.description;
            listing.category = json.category;

            if(listing.category == 'books'){
                book.title = json.title;
                book.author = json.author;
                book.isbn = json.isbn;
                book.course = json.assignedCourse;
                book.condition = json.condition;
                book.type = json.bookType;
            }

            else if(listing.category == 'furniture'){
                furniture.type = json.furnitureType;
                furnitue.condition = json.condition;

            }

            else if(listing.category == 'electronics'){
                
            }

            else if(listing.category == 'bikes'){
                bike.type = json.bikeType;
                bike.make = json.make;
                bike.nodel = json.model;
            }
        }
        console.log();
        addContact();
        addListing();
    }
    request.send();
}


function addContact(){
    $('#name').html('Name: '+contact.name);
    $('#email').html('Email: '+contact.email);
    $('#phone').html('Phone: '+contact.phone);
}

function addListing(){
    $('#title').html(listing.title);
    $('#name').html('Price: ' + listing.price)
    $('#description').append('<h5>'+listing.description+'</h5>');
    if (listing.category == 'book'){
        $('#dependantInfo').append('<h4> Title: '+book.title+'</h4>');
        $('#dependantInfo').append('<h4> Author: '+book.author+'</h4>');
        $('#dependantInfo').append('<h4> Type: '+book.type+'</h4>');
        $('#dependantInfo').append('<h4> Condition: '+book.condition+'</h4>');
        $('#dependantInfo').append('<h4> ISBN: '+book.isbn+'</h4>');
        $('#dependantInfo').append('<h4> Assigned Course: '+book.course+'</h4>');
    }
    else if (listing.category == 'furniture'){
        $('#dependantInfo').append('<h4> Type: '+furniture.type+'</h4>');
        $('#dependantInfo').append('<h4> condition: '+furniture.condition+'</h4>');
    }
    else if (listing.category == 'electronics'){
        $('#dependantInfo').append('<h4> Type: '+electronic.type+'</h4>');
        $('#dependantInfo').append('<h4> Make: '+electronic.make+'</h4>');
        $('#dependantInfo').append('<h4> Model: '+electronic.model+'</h4>');
    }
    else if (listing.category == 'bikes'){
        $('#dependantInfo').append('<h4> Type: '+bike.type+'</h4>');
        $('#dependantInfo').append('<h4> Make: '+bike.make+'</h4>');
        $('#dependantInfo').append('<h4> Model: '+bike.model+'</h4>');
    }
}