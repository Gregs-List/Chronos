package com.example.gregslist;

import java.io.IOException;
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

import com.example.gregslist.CustomAdapter;
import com.example.gregslist.R;
import com.example.gregslist.LoginActivity.DownloadFilesTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.gregslist.Listing;

public class UserHome extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);
		Bundle b = getIntent().getExtras();
		final int value = b.getInt("id");
		//TextView id = (TextView) findViewById(R.id.user);
		//id.setText(String.valueOf(value));
		
		String[] categories = {"Bikes","Books","Electronics","Furniture","Meetups","Rides","Miscellaneous"};
	
		DrawerLayout mDrawerLayout;
		ListView mDrawerList;
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.drawer_list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				R.layout.drawer_list_item, categories);
		mDrawerList.setAdapter(adapter);
		    
		new DownloadFilesTask().execute("http://ec2-50-112-191-198.us-west-2.compute.amazonaws.com/GregsList/Android_API/listings.php");
		
		Button account = (Button) findViewById(R.id.account);
		account.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(UserHome.this,AccountActivity.class);
				Bundle c = new Bundle();
	        	   c.putInt("id", value); //Your id
	        	   i.putExtras(c);
				startActivity(i);
			}
		});
		
		Button logout = (Button) findViewById(R.id.logout);
		logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(UserHome.this, HomePage.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_home, menu);
		return true;
	}
	
public class DownloadFilesTask extends AsyncTask<String, Void, String> {

    	protected String doInBackground(String... urls) {
    		String url = urls[0];
    		Log.d("ALD",url);
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
			final ArrayList<Listing> listings = new ArrayList<Listing>();
        	try {
    			JSONObject j = new JSONObject(result);
    			JSONArray jsonPerson = j.getJSONArray("listings");
    			for (int counter = 0; counter < 10; counter++) {
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
    				titles.add(listings.get(i).getTitle());
    				categories.add(listings.get(i).getCategory());
    			}
    			
    			
    			
    			final ListView listview = (ListView) findViewById(R.id.listview);
    	        final ArrayAdapter adapter;
    		    adapter = new CustomAdapter(UserHome.this,titles,categories);
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
    	              Intent i = new Intent(UserHome.this, ListingActivity.class);
    	              Bundle b = new Bundle();
		        	  b.putString("id", listings.get(position).getListingID());
		        	  b.putString("category", listings.get(position).getCategory());
		        	  b.putString("user", listings.get(position).getUserID());
		        	  b.putString("title", listings.get(position).getTitle());
		        	  b.putString("loggedIn", String.valueOf(value));
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
