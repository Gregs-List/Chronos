$(document).ready(function(){


	$('#category').change(function(e){
		var text = $('#category :selected').text();
		if(text == 'Books'){
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
		else if (text == 'Furniture'){
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
		else if (text == 'Electronics'){
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
		else if (text == 'Bikes'){
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
		else if (text == 'Miscellaneous'){
			hideFields();
		}
	});

	$('#newListing').click(function(e){
		var money = $('#price').val();
		money = parseMoney(money);

		if(money > 9999.99){
			e.preventDefault();
			alert("Price cannot exceed $9999.99");
		}
		else{
			$('#price').val(money);
			alert("Listing created. You will be redirected to the home page.");
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
