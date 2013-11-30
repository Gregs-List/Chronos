package com.example.gregslist;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

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

import com.example.gregslist.LoginActivity.DownloadFilesTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		Bundle b = getIntent().getExtras();
		final int value = b.getInt("id");
		//Log.d("ALD",String.valueOf(value));
		//TextView id = (TextView) findViewById(R.id.user_id);
		//id.setText(String.valueOf(value));
		
		Typeface typeFace = Typeface.createFromAsset(this.getAssets(),"fonts/SuperClarendon.ttc");
		Typeface bold = Typeface.createFromAsset(this.getAssets(), "fonts/CLARENDO.TTF");
		
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_account));
		actionbar.setDisplayShowHomeEnabled(false);
		actionbar.setDisplayShowTitleEnabled(false);
		
		TextView title = (TextView) findViewById(R.id.title);
		TextView category = (TextView) findViewById(R.id.category);
		title.setTypeface(bold);
		category.setTypeface(bold);
		
		TextView account_heading = (TextView) findViewById(R.id.contact);
		account_heading.setTypeface(bold);
		
		TextView email_account = (TextView) findViewById(R.id.email_account);
		email_account.setTypeface(typeFace);
		
		TextView phone_account = (TextView) findViewById(R.id.phone);
		phone_account.setTypeface(typeFace);
		
		TextView my_listings = (TextView) findViewById(R.id.mylisting);
		my_listings.setTypeface(bold);
		
		
		Button account = (Button) findViewById(R.id.account);
		account.setTypeface(typeFace);
		
		Button logout = (Button) findViewById(R.id.logout);
		logout.setTypeface(typeFace);
		logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(AccountActivity.this, HomePage.class);
				Bundle d = new Bundle();
	        	d.putInt("id", value); //Your id
	        	i.putExtras(d);
				startActivity(i);
			}
		});
		
		Button home = (Button) findViewById(R.id.home);
		home.setTypeface(typeFace);
		home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(AccountActivity.this, UserHome.class);
				Bundle d = new Bundle();
	        	d.putInt("id", value); //Your id
	        	i.putExtras(d);
				startActivity(i);
				
			}
		});
		
		String url = "http://ec2-50-112-191-198.us-west-2.compute.amazonaws.com/GregsList/Android_API/account_details.php";
    	StringBuilder text = new StringBuilder().append(url).append("?userID=").append(String.valueOf(value));
        String full_url = text.toString();
        String urls[] = new String[2];
        urls[0] = full_url;
        String url2 = "http://ec2-50-112-191-198.us-west-2.compute.amazonaws.com/GregsList/Android_API/my_listings.php";
		StringBuilder text2 = new StringBuilder().append(url2).append("?userID=").append(String.valueOf(value));
        urls[1] = text2.toString();
		new DownloadFilesTask().execute(urls);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
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
           HttpGet get2 = new HttpGet(url2);
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
        	Log.d("ALD",result2);
        	JSONObject object;
        	StringBuilder phone_line;
        	try {
				object = new JSONObject(result);
				String email_get = object.getString("email");
				String phone_get = object.getString("phone");
	        	TextView email = (TextView) findViewById(R.id.email_account);
	        	StringBuilder email_line = new StringBuilder().append("Email Address: ").append(email_get);
	        	email.setText(email_line.toString());
	        	TextView phone = (TextView) findViewById(R.id.phone);
	        	if (phone_get.equals("null")) {
	        		phone_line = new StringBuilder().append("Phone Number: No phone number");
	        	} else {
	        	    phone_line = new StringBuilder().append("Phone Number: ").append(phone_get);
	        	}
	        	phone.setText(phone_line.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
        	final ArrayList<Listing> listings = new ArrayList<Listing>();
        	try {
    			JSONObject j = new JSONObject(result2);
    			JSONArray jsonPerson = j.getJSONArray("listings");
    			Log.d("ALD",String.valueOf(jsonPerson.length()));
    			for (int counter = 0; counter<jsonPerson.length(); counter++) {
        			String listing_id = ((JSONObject)jsonPerson.get(counter)).getString("listingID");
        			String user_id = ((JSONObject)jsonPerson.get(counter)).getString("userListingID");
        			String title = ((JSONObject)jsonPerson.get(counter)).getString("title");
        			String dateListed = ((JSONObject)jsonPerson.get(counter)).getString("dateListed");
        			String category = ((JSONObject)jsonPerson.get(counter)).getString("category");
        			String price = ((JSONObject)jsonPerson.get(counter)).getString("price");
        			String description = ((JSONObject)jsonPerson.get(counter)).getString("description");
    				Listing new_listing = new Listing(listing_id,user_id,title,dateListed,category,price,description);
    				listings.add(new_listing);
    			}
    			
    			ArrayList<String> titles = new ArrayList<String>();
    			ArrayList<String> categories = new ArrayList<String>();
    			
    			for (int i = 0; i < listings.size(); i++) {
    				Log.d("ALD","meow");
    				titles.add(listings.get(i).getTitle());
    				Log.d("ALD",listings.get(i).getTitle());
    				categories.add(listings.get(i).getCategory());
    				Log.d("ALD",listings.get(i).getCategory());
    			}
    			
    			final ListView listview = (ListView) findViewById(R.id.listview);
    	        final ArrayAdapter adapter;
    		    adapter = new CustomAdapter(AccountActivity.this,titles,categories);
    	        listview.setAdapter(adapter);
    	        
    	        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    	            @Override
    	            public void onItemClick(AdapterView<?> parent, final View view,
    	                int position, long id) {
    	              //final Listing item = (Listing) parent.getItemAtPosition(position);
    	              Log.d("ALD",listings.get(position).getTitle());
		        	  Bundle a = getIntent().getExtras();
		      		  final int value = a.getInt("id");
		      		  Log.d("ALD",String.valueOf(value));
    	              Intent i = new Intent(AccountActivity.this, ListingActivity.class);
    	              Bundle b = new Bundle();
		        	  b.putString("id", listings.get(position).getListingID());
		        	  b.putString("category", listings.get(position).getCategory());
		        	  b.putString("user", listings.get(position).getUserID());
		        	  b.putString("title", listings.get(position).getTitle());
		        	  b.putString("loggedIn", String.valueOf(value));
		        	  b.putString("price", listings.get(position).getPrice());
		        	  b.putString("description", listings.get(position).getDescription());
		        	  b.putString("dateListed", listings.get(position).getDateListed());
		        	  i.putExtras(b);
    	              startActivity(i);
    	            }

    	          });
    	        
    		} catch (JSONException e) {
    			e.printStackTrace();
    		}
    }
}
}
