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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ListingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listing);
		Bundle b = getIntent().getExtras();
		final String listingID = b.getString("id");
		final String category = b.getString("category");
		final String userID = b.getString("user");
		final String title = b.getString("title");
		final String loggedIn = b.getString("loggedIn");
		Log.d("ALD",loggedIn);
		final TextView title_textview = new TextView(this);
		title_textview.setText(title);
		RelativeLayout listLayout= (RelativeLayout) findViewById(R.id.listing_view);
		//listLayout.addView(title_textview)
        
        Button account = (Button) findViewById(R.id.account);
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
        new DownloadFilesTask().execute(full_url);
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
    		String result = " ";
        	HttpClient client = new DefaultHttpClient();
        	HttpGet get = new HttpGet(url);
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
           return result;
        }

        protected void onPostExecute(String result) {
        	Log.d("ALD",result);
        	JSONObject object;
			try {
				object = new JSONObject(result);
	        	String name = object.getString("name");
	        	String email = object.getString("email");
	        	String location = object.getString("location");
	        	String phone = object.getString("phoneNumber");
	        	
	        	TextView listing_name = (TextView) findViewById(R.id.listing_name);
	        	StringBuilder name_field = new StringBuilder().append("Name: ").append(name);
	        	listing_name.setText(name_field.toString());
	        	TextView listing_email = (TextView) findViewById(R.id.listing_email);
	        	StringBuilder email_field = new StringBuilder().append("Email: ").append(email);
	        	listing_email.setText(email_field.toString());
	        	TextView listing_location = (TextView) findViewById(R.id.listing_location);
	        	StringBuilder location_field;
	        	if (location.equals("null")) {
	        		 location_field = new StringBuilder().append("Location: No location");
	        	} else {
	        		location_field = new StringBuilder().append("Location: ").append(location);
	        	}
	        	listing_location.setText(location_field.toString());
	        	
	        	TextView listing_phone = (TextView) findViewById(R.id.listing_phone);
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
        }
    }
 
}
