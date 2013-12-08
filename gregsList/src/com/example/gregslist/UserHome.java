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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.example.gregslist.Listing;

public class UserHome extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Bundle b = getIntent().getExtras();
		final int value = b.getInt("id");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_home));
		actionbar.setDisplayShowHomeEnabled(false);
		actionbar.setDisplayShowTitleEnabled(false);
		
		Typeface typeFace = Typeface.createFromAsset(this.getAssets(),"fonts/DenkOne-Regular.ttf");
		//Typeface bold = Typeface.createFromAsset(this.getAssets(), "fonts/CLARENDO.TTF");
		
		Button home = (Button) findViewById(R.id.nav1);
		home.setTypeface(typeFace);
		
		TextView heading = (TextView) findViewById(R.id.user_activity);
		heading.setTypeface(typeFace);
		
		TextView title = (TextView) findViewById(R.id.title);
		TextView category = (TextView) findViewById(R.id.category);
		title.setTypeface(typeFace);
		category.setTypeface(typeFace);
		
		EditText search_hint = (EditText) findViewById(R.id.search_text);
		search_hint.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER);
		search_hint.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (event != null&& (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
					EditText text = (EditText) findViewById(R.id.search_text);
					if (text.getText().toString().trim().equals("")) {
						final int duration = Toast.LENGTH_SHORT;
						Toast toast1 = Toast.makeText(getApplicationContext(), "Enter a search term", duration);
			        	toast1.show();
					} else {
						Intent i = new Intent(UserHome.this,SearchActivity.class);
						Bundle d = new Bundle();
			        	   d.putInt("id", value); //Your id
			        	   d.putString("search_term", text.getText().toString());
			        	   i.putExtras(d);
						   startActivity(i);
					}
					
				}
				return false;
			}
		});
		
		search_hint.setTypeface(typeFace);
		
		//TextView id = (TextView) findViewById(R.id.user);
		//id.setText(String.valueOf(value));
		
		String[] categories = {"Bikes","Books","Electronics","Furniture","Meetups","Rides","Miscellaneous"};

		    
		new DownloadFilesTask().execute("http://ec2-50-112-191-198.us-west-2.compute.amazonaws.com/GregsList/Android_API/listings.php");
		Button account = (Button) findViewById(R.id.account);
		account.setTypeface(typeFace);
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
		logout.setTypeface(typeFace);
		logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(UserHome.this, HomePage.class);
				startActivity(i);
			}
		});
		
		ImageButton search = (ImageButton) findViewById(R.id.search);
		search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText text = (EditText) findViewById(R.id.search_text);
				if (text.getText().toString().trim().equals("")) {
					final int duration = Toast.LENGTH_SHORT;
					Toast toast1 = Toast.makeText(getApplicationContext(), "Enter a search term", duration);
		        	toast1.show();
				} else {
					Intent i = new Intent(UserHome.this,SearchActivity.class);
					Bundle d = new Bundle();
		        	   d.putInt("id", value); //Your id
		        	   d.putString("search_term", text.getText().toString());
		        	   i.putExtras(d);
					   startActivity(i);
				}
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
	    ProgressDialog pd;
	    
	    @Override
	    protected void onPreExecute() {
	    super.onPreExecute();
	    pd=ProgressDialog.show(UserHome.this,"","Loading Listings...",false);
	    }
	    
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
    			for (int counter = 0; counter < jsonPerson.length(); counter++) {
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
    	        pd.dismiss();
    	        
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
