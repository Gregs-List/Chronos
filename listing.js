


function listing(){
    var title;
    var price;
    var description;
    var category;
    var date;
    var photo;
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

var listing = new listing();
var contact = new contact();
var book = new book();
var furniture = new furniture();
var electronic = new electronic();
var bike = new bike();

function fillListing(data){

    var request = new XMLHttpRequest();
    var json;
    var url = "viewFullListingJSON.php?listingID="+data;
    request.open("GET", url, true);

    request.onreadystatechange = function(e)
    {
        if(request.readyState == 4){
            console.log(request.responseText);
            json = JSON.parse(request.responseText);
            console.log(json);

            

            contact.name = json.fullName;
            contact.email = json.email;
            contact.phone = json.phoneNumber;

            listing.title = json.title;
            listing.date = json.dateListed;
            listing.price = json.price;
            listing.description = json.description;
            listing.category = json.category;
            listing.photo = json.photoURL;
            console.log(listing);

            if(listing.category == 'Books'){
                book.title = json.title;
                book.author = json.author;
                book.isbn = json.isbn;
                book.course = json.assignedCourse;
                book.condition = json.itemCondition;
                book.type = json.bookType;
            }

            else if(listing.category == "Furniture"){
                furniture.type = json.furnitureType;
                furniture.condition = json.itemCondition;

            }

            else if(listing.category == 'Electronics'){
                electronic.type = json.electronicsType;
                electronic.make = json.make;
                electronic.model = json.model;
            }

            else if(listing.category == 'Bikes'){
                bike.type = json.bikeType;
                bike.make = json.make;
                bike.model = json.model;
            }
        }
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
    $('#price').html('Price: ' + listing.price)
    $('#category').html('Category: ' + listing.category);
    $('#description').html('Description: ' + listing.description);
    if (listing.category == 'Books'){
        $('#dependantInfo').append('<h4> Title: '+book.title+'</h4>');
        $('#dependantInfo').append('<h4> Author: '+book.author+'</h4>');
        $('#dependantInfo').append('<h4> Type: '+book.type+'</h4>');
        $('#dependantInfo').append('<h4> Condition: '+book.condition+'</h4>');
        $('#dependantInfo').append('<h4> ISBN: '+book.isbn+'</h4>');
        $('#dependantInfo').append('<h4> Assigned Course: '+book.course+'</h4>');
    }
    else if (listing.category == "Furniture"){
        $('#dependantInfo').append('<h4> Type: '+furniture.type+'</h4>');
        $('#dependantInfo').append('<h4> Condition: '+furniture.condition+'</h4>');
    }
    else if (listing.category == 'Electronics'){
        $('#dependantInfo').append('<h4> Type: '+electronic.type+'</h4>');
        $('#dependantInfo').append('<h4> Make: '+electronic.make+'</h4>');
        $('#dependantInfo').append('<h4> Model: '+electronic.model+'</h4>');
    }
    else if (listing.category == 'Bikes'){
        $('#dependantInfo').append('<h4> Type: '+bike.type+'</h4>');
        $('#dependantInfo').append('<h4> Make: '+bike.make+'</h4>');
        $('#dependantInfo').append('<h4> Model: '+bike.model+'</h4>');
    }

    if(listing.photo == "No photo"){
        $('#listPhoto').css("display", "none");
    }
    else{
        $('#listPhoto').attr("src", "User_Photos/" + listing.photo);
    }
    
}

$(document).ready( function() {


    var data = window.location.search;

    
    if (data.substring(0, 1) == '?') {
        data = data.substring(1);
        fillListing(data);
    }   
    
});