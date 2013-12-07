$(document).ready(function(){


	$('#category').change(function(e){
		var text = $('#category :selected').text();
		if(text == 'Books'){
			hideFields();
			$('#bookFields').css('display','Block');
		}
		else if (text == 'Furniture'){
			hideFields();
			$('#furnitureFields').css('display','Block');
		}
		else if (text == 'Electronics'){
			hideFields();
			$('#electronicFields').css('display','Block');
		}
		else if (text == 'Bikes'){
			hideFields();
			$('#bikeFields').css('display','Block');
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
			window.location="home.html";
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