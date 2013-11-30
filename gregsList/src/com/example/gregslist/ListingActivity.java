package com.example.gregslist;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.gregslist.UserHome.DownloadFilesTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ListingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listing);
		
		Typeface typeFace = Typeface.createFromAsset(this.getAssets(),"fonts/SuperClarendon.ttc");
		Typeface bold = Typeface.createFromAsset(this.getAssets(), "fonts/CLARENDO.TTF");
		
		TextView contact = (TextView) findViewById(R.id.contact);
		contact.setTypeface(bold);
		
		TextView header = (TextView) findViewById(R.id.listings_header);
		header.setTypeface(bold);
		
		Bundle b = getIntent().getExtras();
		final String listingID = b.getString("id");
		final String category = b.getString("category");
		final String userID = b.getString("user");
		final String title = b.getString("title");
		StringBuilder full_title = new StringBuilder().append("Title: ").append(title);
		final String loggedIn = b.getString("loggedIn");
		final String description = b.getString("description");
		StringBuilder full_description = new StringBuilder().append("Description: ").append(description);
		final String price = b.getString("price");
		StringBuilder full_price = new StringBuilder().append("Price: $").append(price);
		final String dateListed = b.getString("dateListed");
		StringBuilder full_date = new StringBuilder().append("Date Listed: ").append(dateListed);
		Log.d("ALD",loggedIn);
		LinearLayout listLayout= (LinearLayout) findViewById(R.id.details);
		final TextView title_textview = new TextView(this);
		title_textview.setText(full_title);
		title_textview.setTypeface(typeFace);
		listLayout.addView(title_textview);
		final TextView description_textview = new TextView(this);
		description_textview.setText(full_description);
		description_textview.setTypeface(typeFace);
		listLayout.addView(description_textview);
		final TextView price_textview = new TextView(this);
		price_textview.setText(full_price);
		price_textview.setTypeface(typeFace);
		listLayout.addView(price_textview);
		final TextView date_textview = new TextView(this);
		date_textview.setText(full_date);
		date_textview.setTypeface(typeFace);
		listLayout.addView(date_textview);
		
        Button account = (Button) findViewById(R.id.account);
		account.setTypeface(typeFace);
        account.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(ListingActivity.this, AccountActivity.class);
				Bundle b = new Bundle();
				b.putInt("id", Integer.parseInt(loggedIn));
	        	i.putExtras(b);
	            startActivity(i);
			}
		});
        
        Button home = (Button) findViewById(R.id.home);
		home.setTypeface(typeFace);
        home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ListingActivity.this, UserHome.class);
				Bundle b = new Bundle();
				b.putInt("id", Integer.parseInt(loggedIn));
	        	i.putExtras(b);
	            startActivity(i);
			}
		});
        
        Button logout = (Button) findViewById(R.id.logout);
		logout.setTypeface(typeFace);
        logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ListingActivity.this, HomePage.class);
				startActivity(i);
				
			}
		});
		String url = "http://ec2-50-112-191-198.us-west-2.compute.amazonaws.com/GregsList/Android_API/listing_contact_info.php";
    	StringBuilder text = new StringBuilder().append(url).append("?userID=").append(userID);
        String full_url = text.toString();
        String urls[] = new String[2];
        urls[0] = full_url;
        String url2 = "http://ec2-50-112-191-198.us-west-2.compute.amazonaws.com/GregsList/Android_API/listing_view.php";
        StringBuilder text2 = new StringBuilder().append(url2).append("?category=").append(category).append("&listingID=").append(listingID);
        String full_url2 = text2.toString();
        urls[1] = full_url2;
        new DownloadFilesTask().execute(urls);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listing, menu);
		return true;
	}
	
public class DownloadFilesTask extends AsyncTask<String, Void, String> {
        
    	protected String doInBackground(String... urls) {
    		String url = urls[0];
    		String url2 = urls[1];
    		String result = " ";
    		String result2 = " ";
    		
        	HttpClient client = new DefaultHttpClient();
        	HttpGet get = new HttpGet(url);
        	HttpGet get2 = new HttpGet(url2);
        	try {
        		HttpResponse response = client.execute(get);
        		HttpEntity entity = response.getEntity();
        			if (null != entity) {
        				result = EntityUtils.toString(entity);  
        			}
        	} catch (ClientProtocolException e) {
        		e.printStackTrace();
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        	
        	try {
        		HttpResponse response2 = client.execute(get2);
        		HttpEntity entity2 = response2.getEntity();
        			if (null != entity2) {
        				result2 = EntityUtils.toString(entity2);  
        			}
        	} catch (ClientProtocolException e) {
        		e.printStackTrace();
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        	
           return result + "#" + result2;
        }

        protected void onPostExecute(String result) {
        	String[] separated = result.split("#");
        	String result1 = separated[0];
        	String result2 = separated[1];
        	JSONObject object;
        	final Typeface typeFace = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/SuperClarendon.ttc");
    		final Typeface bold = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/CLARENDO.TTF");
			try {
				object = new JSONObject(result1);
	        	String name = object.getString("name");
	        	String email = object.getString("email");
	        	String location = object.getString("location");
	        	String phone = object.getString("phoneNumber");
	        	
	        	TextView listing_name = (TextView) findViewById(R.id.listing_name);
	        	listing_name.setTypeface(typeFace);
	        	StringBuilder name_field = new StringBuilder().append("Name: ").append(name);
	        	listing_name.setText(name_field.toString());
	        	TextView listing_email = (TextView) findViewById(R.id.listing_email);
	        	listing_email.setTypeface(typeFace);
	        	StringBuilder email_field = new StringBuilder().append("Email: ").append(email);
	        	listing_email.setText(email_field.toString());
	        	TextView listing_location = (TextView) findViewById(R.id.listing_location);
	        	listing_location.setTypeface(typeFace);
	        	StringBuilder location_field;
	        	if (location.equals("null")) {
	        		 location_field = new StringBuilder().append("Location: No location");
	        	} else {
	        		location_field = new StringBuilder().append("Location: ").append(location);
	        	}
	        	listing_location.setText(location_field.toString());
	        	
	        	TextView listing_phone = (TextView) findViewById(R.id.listing_phone);
	        	listing_phone.setTypeface(typeFace);
	        	StringBuilder phone_field;
	        	if (phone.equals("null")) {
	        		 phone_field = new StringBuilder().append("Phone Number: No phone number");
	        	} else {
	        		phone_field = new StringBuilder().append("Phone Number: ").append(phone);
	        	}
	        	listing_phone.setText(phone_field.toString());
	        	
	        	
	        	
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			JSONObject object2;
			
			try {
				object2 = new JSONObject(result2);
	        	String category = object2.getString("category");
        		final LinearLayout listLayout = (LinearLayout) findViewById(R.id.details);
	        	if (category.equals("Bikes")) {
	        		String make = object2.getString("make");
	        		String model = object2.getString("model");
	        		String type = object2.getString("type");       		
	        		
	        		TextView make_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_make_textview = new StringBuilder().append("Make: ").append(make);
	        		make_textview.setText(full_make_textview.toString());	   
	        		make_textview.setTypeface(typeFace);
	        		listLayout.addView(make_textview); 
	        		
	        		TextView model_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_model_textview = new StringBuilder().append("Model: ").append(model);
	        		model_textview.setText(full_model_textview);
	        		model_textview.setTypeface(typeFace);
	        		listLayout.addView(model_textview);
	        		
	        		TextView type_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_type_textview = new StringBuilder().append("Type: ").append(type);
	        		type_textview.setText(full_type_textview);
	        		type_textview.setTypeface(typeFace);
	        		listLayout.addView(type_textview);
	        		
	        	} else if (category.equals("Books")) {
	        		String title = object2.getString("title");
	        		String author = object2.getString("author");
	        		String isbn = object2.getString("isbn");
	        		String assignedCourse = object2.getString("assignedCourse");
	        		String type = object2.getString("type");
	        		String condition = object2.getString("condition");
	        		
	        		TextView title_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_title_textview = new StringBuilder().append("Book Title: ").append(title);
	        		title_textview.setText(full_title_textview.toString());	
	        		title_textview.setTypeface(typeFace);
	        		listLayout.addView(title_textview); 
	        		
	        		TextView author_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_author_textview = new StringBuilder().append("Author: ").append(author);
	        		author_textview.setText(full_author_textview.toString());	
	        		author_textview.setTypeface(typeFace);
	        		listLayout.addView(author_textview); 
	        		
	        		TextView isbn_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_isbn_textview = new StringBuilder().append("ISBN: ").append(isbn);
	        		isbn_textview.setText(full_isbn_textview.toString());	      
	        		isbn_textview.setTypeface(typeFace);
	        		listLayout.addView(isbn_textview); 
	        		
	        		TextView course_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_course_textview = new StringBuilder().append("Assigned Course: ").append(assignedCourse);
	        	    course_textview.setText(full_course_textview.toString());	    
	        	    course_textview.setTypeface(typeFace);
	        		listLayout.addView(course_textview); 
	        		
	        		TextView type_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_type_textview = new StringBuilder().append("Type: ").append(type);
	        		type_textview.setText(full_type_textview.toString());	
	        		type_textview.setTypeface(typeFace);
	        		listLayout.addView(type_textview); 
	        		
	        		TextView condition_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_condition_textview = new StringBuilder().append("Condition: ").append(condition);
	        		condition_textview.setText(full_condition_textview.toString());	 
	        		condition_textview.setTypeface(typeFace);
	        		listLayout.addView(condition_textview); 
	        		
	        	} else if (category.equals("Electronics")) {
	        		String make = object2.getString("make");
	        		String model = object2.getString("model");
	        		String type = object2.getString("type"); 
	        		String size = object2.getString("size");
	        		
	        		TextView make_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_make_textview = new StringBuilder().append("Make: ").append(make);
	        		make_textview.setText(full_make_textview.toString());	     
	        		make_textview.setTypeface(typeFace);
	        		listLayout.addView(make_textview); 
	        		
	        		TextView model_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_model_textview = new StringBuilder().append("Model: ").append(model);
	        		model_textview.setText(full_model_textview);
	        		model_textview.setTypeface(typeFace);
	        		listLayout.addView(model_textview);
	        		
	        		TextView type_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_type_textview = new StringBuilder().append("Type: ").append(type);
	        		type_textview.setText(full_type_textview);
	        		type_textview.setTypeface(typeFace);
	        		listLayout.addView(type_textview);
	        		
	        		TextView size_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_size_textview = new StringBuilder().append("Size: ").append(size);
	        		size_textview.setText(full_size_textview);
	        		size_textview.setTypeface(typeFace);
	        		listLayout.addView(size_textview);
	        		
	        	} else if (category.equals("Furniture")) {
	        		
	        		String type = object2.getString("type"); 
	        		String condition = object2.getString("condition");
	        		
	        		TextView type_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_type_textview = new StringBuilder().append("Type: ").append(type);
	        		type_textview.setText(full_type_textview);
	        		type_textview.setTypeface(typeFace);
	        		listLayout.addView(type_textview);
	        		
	        		TextView condition_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_condition_textview = new StringBuilder().append("Condition: ").append(condition);
	        		condition_textview.setText(full_condition_textview.toString());	
	        		condition_textview.setTypeface(typeFace);
	        		listLayout.addView(condition_textview); 
	        	
	        	} else if (category.equals("Meetups")) {
	        		
	        		String type = object2.getString("type");
	        		String location = object2.getString("location");
	        		String date = object2.getString("date");
	        		String time = object2.getString("time");
	        		
	        		TextView type_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_type_textview = new StringBuilder().append("Type: ").append(type);
	        		type_textview.setText(full_type_textview);
	        		type_textview.setTypeface(typeFace);
	        		listLayout.addView(type_textview);
	        		
	        		TextView location_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_location_textview = new StringBuilder().append("Location: ").append(location);
	        		location_textview.setText(full_location_textview);
	        		location_textview.setTypeface(typeFace);
	        		listLayout.addView(location_textview);
	        		
	        		TextView date_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_date_textview = new StringBuilder().append("Date: ").append(date);
	        		date_textview.setText(full_date_textview);
	        		date_textview.setTypeface(typeFace);
	        		listLayout.addView(date_textview);
	        		
	        		TextView time_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_time_textview = new StringBuilder().append("Time: ").append(time);
	        		time_textview.setText(full_time_textview);
	        		time_textview.setTypeface(typeFace);
	        		listLayout.addView(time_textview);
	        		
	        	} else if (category.equals("Miscellaneous")) {
	        		
	        		String name = object2.getString("name");
	        		
	        		TextView name_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_name_textview = new StringBuilder().append("Item Name: ").append(name);
	        		name_textview.setText(full_name_textview);
	        		name_textview.setTypeface(typeFace);
	        		listLayout.addView(name_textview);
	        		
	        	} else if (category.equals("Rides")) {
	        		
	        		String leavingFrom = object2.getString("leavingFrom");
	        		String goingTo = object2.getString("goingTo");
	        		String departureDate = object2.getString("departureDate");
	        		String departureTime = object2.getString("departureTime");
	        		String returnDate = object2.getString("returnDate");
	        		String returnTime = object2.getString("returnTime");
	        		
	        		TextView leavingFrom_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_leavingFrom_textview = new StringBuilder().append("Leaving From: ").append(leavingFrom);
	        		leavingFrom_textview.setText(full_leavingFrom_textview);
	        		leavingFrom_textview.setTypeface(typeFace);
	        		listLayout.addView(leavingFrom_textview);
	        		
	        		TextView goingTo_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_goingTo_textview = new StringBuilder().append("Going To: ").append(goingTo);
	        		goingTo_textview.setText(full_goingTo_textview);
	        		goingTo_textview.setTypeface(typeFace);
	        		listLayout.addView(goingTo_textview);
	        		
	        		TextView departureDate_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_departureDate_textview = new StringBuilder().append("Departure Date: ").append(departureDate);
	        		departureDate_textview.setText(full_departureDate_textview);
	        		departureDate_textview.setTypeface(typeFace);
	        		listLayout.addView(departureDate_textview);
	        		
	        		TextView returnDate_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_returnDate_textview = new StringBuilder().append("Return Date: ").append(returnDate);
	        		returnDate_textview.setText(full_returnDate_textview);
	        		returnDate_textview.setTypeface(typeFace);
	        		listLayout.addView(returnDate_textview);
	        		
	        		TextView returnTime_textview = new TextView(ListingActivity.this);
	        		StringBuilder full_returnTime_textview = new StringBuilder().append("Return Time: ").append(returnTime);
	        		returnTime_textview.setText(full_returnTime_textview);
	        		returnTime_textview.setTypeface(typeFace);
	        		listLayout.addView(returnTime_textview);
	        	}

			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
        }
        
}
