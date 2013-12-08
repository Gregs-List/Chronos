// requestAnim shim layer by Paul Irish
    window.requestAnimFrame = (function(){
      return  window.requestAnimationFrame       || 
              window.webkitRequestAnimationFrame || 
              window.mozRequestAnimationFrame    || 
              window.oRequestAnimationFrame      || 
              window.msRequestAnimationFrame     || 
              function(/* function */ callback, /* DOMElement */ element){
                window.setTimeout(callback, 1000 / 60);
              };
    })();
  

// example code from mr doob : http://mrdoob.com/lab/javascript/requestanimationframe/

animate(); 

var mLastFrameTime = 0;
var mWaitTime = 8000; //time in ms
function animate() {
    requestAnimFrame( animate );
	var currentTime = new Date().getTime();
	if (mLastFrameTime === 0) {
		mLastFrameTime = currentTime;
	}

	if ((currentTime - mLastFrameTime) > mWaitTime) {
		swapPhoto();
		mLastFrameTime = currentTime;
	}
}

/************* DO NOT TOUCH CODE ABOVE THIS LINE ***************/
var counter = 0; //set to the current image

function swapPhoto() {
	//Add code here to access the #slideShow element.
	//Access the child img element and replace its source
	//with a new image from your images array which is loaded 
	//from the JSON string
	var len = mImages.length;
	if(counter === len-1){
		counter = -1;
	}
	var src = mImages[counter+1].img.src;
	$('#slideShow img').eq(0).attr('src',src);
	$('.details p').eq(0).html("Title: " + mImages[counter+1].title);
	$('.details p').eq(1).html("Price: " + mImages[counter+1].price);
	$('.details p').eq(2).html("Description: "+ mImages[counter+1].description);
	counter++;
	//console.log(src);
	console.log('swap photo');
}

function swapPhotoBack() {
	//Add code here to access the #slideShow element.
	//Access the child img element and replace its source
	//with a new image from your images array which is loaded 
	//from the JSON string
	var len = mImages.length;
		if(counter === 0){
			counter = len;
	}
	var src = mImages[counter-1].img.src;
	$('#slideShow img').eq(0).attr('src',src);
	$('.details p').eq(0).html("Title: " + mImages[counter-1].title);
	$('.details p').eq(1).html("Price: " + mImages[counter-1].price);
	$('.details p').eq(2).html("Description: "+ mImages[counter-1].description);
	counter--;
	//console.log(src);
	console.log('swap photo');
}


//Use this array to hold objects which contain the following:
//location, description, date and an actual Image element.
var mCurrentIndex = 0;
var request = new XMLHttpRequest();
var mImages = [];
var json;
var url = "fiveMostRecent.php";
request.open("GET", url, true);

request.onreadystatechange = function(e)
{
	if(request.readyState == 4){
		console.log(request.responseText);
		json = JSON.parse(request.responseText);
		console.log(json);
		for(var x = 0; x < json.length; x++){
			var gImage = new GalleryImage();
			gImage.title = json[x].title;
			gImage.price = json[x].price;
			gImage.description = json[x].description;
			gImage.id = json[x].listingID;
			gImage.img = new Image();
			gImage.img.src = "User_Photos/" + json[x].photoURL;
			makeGalleryImageOnloadCallback(gImage);
			mImages.push(gImage);
		}
	}
	console.log(mImages);
}
request.send();



//This is probably seems confusing, but in javascript there is a concept known as closures. Closures allows
//variables to remain in scope after a method finishes executing. You can read about it here: http://stackoverflow.com/a/2803496
//All you need to know is that you should use the following function as your event callback for loading the source of Images from your json data.

//@param A GalleryImage object. Use this method for an event handler for loading a gallery Image object.
function makeGalleryImageOnloadCallback(galleryImage) {
	return function(e) {
		galleryImage.img = e.target;
	}
}

function GalleryImage(){
	var title;
	var price;
	var description;
	var img;
	var id;
}





$(document).ready( function() {
	$('.details').eq(0).hide();
	var widthParent = $('.moreIndicator').eq(0).parent().width();
	var offset = widthParent/2 - 25;
	$('.moreIndicator').eq(0).css({
		'position': 'absolute',
		'left' : offset + "px",
		'top' : '325px'
		});
	$('img.moreIndicator').eq(0).click(function(){
		//alert( "Handler for .click() called." );
		if($('img.moreIndicator').eq(0).hasClass("rot90")){
			$('img.moreIndicator').eq(0).removeClass("rot90");
			$('img.moreIndicator').eq(0).addClass("rot270")
			$('.details').eq(0).show();
		}
		else{
			$('img.moreIndicator').eq(0).removeClass("rot270");
			$('img.moreIndicator').eq(0).addClass("rot90")
			$('.details').eq(0).hide();
		}
	});
	$('#nextPhoto').click(function(){
		//alert( "Handler for .click() called." );
		swapPhoto();
	});
	$('#prevPhoto').click(function(){
		//alert( "Handler for .click() called." );
		swapPhotoBack();
	});

	$('#slideShow img').eq(0).click(function(){
		window.location="listing.html?"+mImages[counter].id;
	});
});




window.addEventListener('load', function() {
	//console.log('window loaded');
	var width = $('#nextPhoto').width()*2;
	var parentW = $('#nextPhoto').parent().width();
	var leftOffset = parentW - width - 5;
	$('#nextPhoto').css({'left':leftOffset + "px", 'position' : 'relative'});

}, false);


