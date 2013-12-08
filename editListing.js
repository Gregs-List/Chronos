$(document).ready(function(){

	var data = window.location.search;

    
    if (data.substring(0, 1) == '?') {
        data = data.substring(1);
        findListing(data);
    }   

	

	$('#editListing').click(function(e){

		var money = $('#price').val();
		money = parseMoney(money);

		if(money > 9999.99){
			e.preventDefault();
			alert("Price cannot exceed $9999.99");
		}
		else{
			$('#price').val(money);
			alert("Changes Saved. You will be redirected to your account page.");
		}
	});

});


function hideFields(){
	$('#bookFields').css('display','none');
	$('#furnitureFields').css('display','none');
	$('#electronicFields').css('display','none');
	$('#bikeFields').css('display','none');
}

function parseMoney(money){
	var newMoney = [];
	for(var x =0; x < money.length; x++){
		if(money[x]!= '$' && money[x]!= ','  && money[x]!= '-'){
			newMoney.push(money[x]);
		}

	}

	var newMoneyStr = newMoney.join("");
	return newMoneyStr;
}

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

var listing = new listing();
var contact = new contact();
var book = new book();
var furniture = new furniture();
var electronic = new electronic();
var bike = new bike();

function findListing(data){



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

            

            listing.title = json.title;
            listing.date = json.dateListed;
            listing.price = json.price;
            listing.description = json.description;
            listing.category = json.category;
            console.log(listing);


            if(listing.category == 'Books'){
                book.title = json.title;
                book.author = json.author;
                book.isbn = json.isbn;
                book.course = json.assignedCourse;
                book.condition = json.itemCondition;
                book.type = json.bookTypeID;
            }

            else if(listing.category == "Furniture"){
                furniture.type = json.furnitureTypeID;
                furniture.condition = json.conditionID;

            }

            else if(listing.category == 'Electronics'){
                electronic.type = json.electronicsType;
                electronic.make = json.make;
                electronic.nodel = json.model;
            }

            else if(listing.category == 'Bikes'){
                bike.type = json.bikeType;
                bike.make = json.make;
                bike.model = json.model;
            }

            fillListing(data);
        }

        
    }
    request.send(data);
}

function fillListing(data){
	console.log(listing);
    $('#title').val(listing.title);
    $('#price').val(listing.price)
    $('#category').html('Category: ' + listing.category);
    $('#newListing').attr("action", "editListing.php?category="+listing.category+"&listingID="+data);
    selectFields(listing.category);

    $('#description').html(listing.description);
    if (listing.category == 'Books'){
        $('#BookType').val(book.type);
        $('#bookCondition').find('option[value="'+book.condition+'"]').attr("selected",true);
        $('#bookTitle').val(book.title);
        $('#author').val(book.author);
        $('#isbn').val(book.isbn);
        $('#course').val(book.course);

    }
    else if (listing.category == "Furniture"){
        $('#FurnitureType').find('option[value="'+furniture.type+'"]').attr("selected",true);
        $('#furnitureCondition').find('option[value="'+furniture.condition+'"]').attr("selected",true);
    }
    else if (listing.category == 'Electronics'){
        $('#ElectronicType').find('option[value="'+electronic.type+'"]').attr("selected",true);
        $('#eMake').val(electronic.make);
        $('#eModel').val(+electronic.model);
    }
    else if (listing.category == 'Bikes'){
        $('#BikeType').find('option[value="'+bike.type+'"]').attr("selected",true);
        $('#bMake').val(bike.make);
        $('#bModel').val(bike.model);
    }
}

function selectFields(category){

		if(category == 'Books'){
			hideFields();
			$('#bookFields').css('display','Block');
			document.getElementById("BookType").required = true;
			document.getElementById("bookCondition").required = true;
			document.getElementById("bookTitle").required = true;
			document.getElementById("FurnitureType").required = false;
			document.getElementById("furnitureCondition").required = false;
			document.getElementById("ElectronicType").required = false;
			document.getElementById("BikeType").required = false;
			document.getElementById("bMake").required = false;
			document.getElementById("bModel").required = false;
		}
		else if (category == 'Furniture'){
			hideFields();
			$('#furnitureFields').css('display','Block');
			document.getElementById("FurnitureType").required = true;
			document.getElementById("furnitureCondition").required = true;
			document.getElementById("BookType").required = false;
			document.getElementById("bookCondition").required = false;
			document.getElementById("bookTitle").required = false;
			document.getElementById("ElectronicType").required = false;
			document.getElementById("BikeType").required = false;
			document.getElementById("bMake").required = false;
			document.getElementById("bModel").required = false;
		}
		else if (category == 'Electronics'){
			hideFields();
			$('#electronicFields').css('display','Block');
			document.getElementById("ElectronicType").required = true;
			document.getElementById("BookType").required = false;
			document.getElementById("bookCondition").required = false;
			document.getElementById("bookTitle").required = false;
			document.getElementById("FurnitureType").required = false;
			document.getElementById("furnitureCondition").required = false;
			document.getElementById("BikeType").required = false;
			document.getElementById("bMake").required = false;
			document.getElementById("bModel").required = false;
		}
		else if (category == 'Bikes'){
			hideFields();
			$('#bikeFields').css('display','Block');
			document.getElementById("BikeType").required = true;
			document.getElementById("bMake").required = true;
			document.getElementById("bModel").required = true;
			document.getElementById("BookType").required = false;
			document.getElementById("bookCondition").required = false;
			document.getElementById("bookTitle").required = false;
			document.getElementById("FurnitureType").required = false;
			document.getElementById("furnitureCondition").required = false;
			document.getElementById("ElectronicType").required = false;
		}
		else if (category == 'Miscellaneous'){
			hideFields();
		}

}