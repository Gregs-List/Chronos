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
var mWaitTime = 5000; //time in ms
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
var counter = 3; //set to the current image

function swapPhoto() {
	//Add code here to access the #slideShow element.
	//Access the child img element and replace its source
	//with a new image from your images array which is loaded 
	//from the JSON string
	var len = mImages.length;
	if(counter === len-1){
		counter = 0;
	}
	var src = mImages[counter+1].img.src;
	$('#slideShow img').eq(0).attr('src',src);
	$('.details p').eq(0).html("Location:" + mImages[counter+1].location);
	$('.details p').eq(1).html("Description:" + mImages[counter+1].description);
	$('.details p').eq(2).html("Date:" + mImages[counter+1].date);
	counter++;
	console.log(src);
	console.log('swap photo');
}



//Use this array to hold objects which contain the following:
//location, description, date and an actual Image element.
var mCurrentIndex = 0;
var request = new XMLHttpRequest();
var mImages = [];
var json;
var url = "http://lyle.smu.edu/~cdewey/3345/a7/htmlPhotoGallery-master/images.json";
request.open("GET", url, true);
request.onreadystatechange = function(e)
{
	if(request.readyState == 4){
		json = JSON.parse(request.responseText);
		
		for(var x = 0; x < json.images.length; x++){
			var gImage = new GalleryImage();
			gImage.location = json.images[x].imgLocation;
			gImage.date = json.images[x].date;
			gImage.description = json.images[x].description;
			gImage.img = new Image();
			gImage.img.src = json.images[x].imgPath;
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
	var location;
	var description;
	var date;
	var img;
}




/*function testClosureExample() {
	for (var i = 0; i < 4; i++) {
		var input = document.createElement("input");
		input.type = "button";
		input.value = "Button " + i;
		//trying to access the value i like the click listener is doing will lead to bad results.
		input.onclick = function(e) {
			console.log("The variable i will always equal 4. i = " + i);
		}
		
		//using a closure to fight a closure gives us what we would expect.
		input.onmouseup = testClosureExampleMouseUpHandler(i);
		document.body.appendChild(input);
	}
}

function testClosureExampleMouseUpHandler(i) {
	return function(e) {
		console.log("The variable is will be what you expect, and match the value the button has. i = " + i);
	}
}*/

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
		var len = mImages.length;
		if(counter === len-1){
			counter = 0;
		}
		var src = mImages[counter+1].img.src;
		$('.bottomPhoto').eq(0).attr('src',src);
		$('.details p').eq(0).html("Location:" + mImages[counter+1].location);
		$('.details p').eq(1).html("Description:" + mImages[counter+1].description);
		$('.details p').eq(2).html("Date:" + mImages[counter+1].date);

		if($('#slideShow img').eq(0).hasClass("topPhoto")){
			var opacity = $('#slideShow img').eq(0).css('opacity');
			$('#slideShow img').eq(0).animate({opacity: (opacity==1?0:1)});
		}
		else{
			var opacity = $('#slideShow img').eq(1).css('opacity');
			$('#slideShow img').eq(1).animate({opacity: (opacity==1?0:1)});
		}

		counter++;
	});
	$('#prevPhoto').click(function(){
		//alert( "Handler for .click() called." );
		var len = mImages.length;
		if(counter === 0){
			counter = len-1;
		}
		var src = mImages[counter-1].img.src;
		$('.bottomPhoto').eq(0).attr('src',src);
		$('.details p').eq(0).html("Location:" + mImages[counter-1].location);
		$('.details p').eq(1).html("Description:" + mImages[counter-1].description);
		$('.details p').eq(2).html("Date:" + mImages[counter-1].date);
		//$('#slideShow img').eq(1).fadeIn();
		//$('#slideShow img').eq(0).fadeOut();
		
		if($('#slideShow img').eq(0).hasClass("topPhoto")){
			var opacity = $('#slideShow img').eq(0).css('opacity');
			$('#slideShow img').eq(0).animate({opacity: (opacity==1?0:1)});
		}
		else{
			var opacity = $('#slideShow img').eq(1).css('opacity');
			$('#slideShow img').eq(1).animate({opacity: (opacity==1?0:1)});
		}
		counter--;
	});
});




window.addEventListener('load', function() {
	//console.log('window loaded');
	//testClosureExample();
	var width = $('#nextPhoto').width()*2;
	var parentW = $('#nextPhoto').parent().width();
	var leftOffset = parentW - width - 5;
	$('#nextPhoto').css({'left':leftOffset + "px", 'position' : 'relative'});

}, false);


