package com.example.gregslist;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
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

import com.example.gregslist.ListingActivity.DownloadFilesTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		Bundle b = getIntent().getExtras();
		final int value = b.getInt("id");
		final String search_term = b.getString("search_term");
		
		String url = "http://ec2-50-112-191-198.us-west-2.compute.amazonaws.com/GregsList/Android_API/search.php";
		StringBuilder full_url = new StringBuilder().append(url).append("?search_term=").append(search_term);
			URI url_spaces;
			try {
				url_spaces = new URI(full_url.toString().replaceAll(" ","%20"));
				Log.d("ALD",url_spaces.toString());
				new DownloadFilesTask().execute(url_spaces.toString());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		ImageButton search = (ImageButton) findViewById(R.id.search);
		search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText new_search_term = (EditText) findViewById(R.id.search_text);
				Intent intent = getIntent();
			    Bundle b = new Bundle();
			    b.putInt("id",value);
			    b.putString("search_term", new_search_term.getText().toString());
			    intent.putExtras(b);
				finish();
				startActivity(intent);
			}
		});
		
        Button account = (Button) findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(SearchActivity.this, AccountActivity.class);
				Bundle c = new Bundle();
				c.putInt("id", value);
	        	i.putExtras(c);
	            startActivity(i);
			}
		});
        
        Button home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SearchActivity.this, UserHome.class);
				Bundle d = new Bundle();
				d.putInt("id", value);
	        	i.putExtras(d);
	            startActivity(i);
			}
		});
        
        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SearchActivity.this, HomePage.class);
				startActivity(i);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
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
    			
    			if (titles.size() == 0) {
    				final int duration = Toast.LENGTH_SHORT;
					Toast toast1 = Toast.makeText(getApplicationContext(), "There are no search results for this search term", duration);
		        	toast1.show();
    			}
    			
    			Button bikes = (Button) findViewById(R.id.bikes);
    			Button books = (Button) findViewById(R.id.books);
    			Button electronics = (Button) findViewById(R.id.electronics);
    			Button furniture = (Button) findViewById(R.id.furniture);
    			Button meetups = (Button) findViewById(R.id.meetups);
    			Button rides = (Button) findViewById(R.id.rides);
    			Button misc = (Button) findViewById(R.id.misc);
    			Button all = (Button) findViewById(R.id.all);
    			
    			bikes.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						ArrayList<String> titles_bikes = new ArrayList<String>();
		    			ArrayList<String> categories_bikes = new ArrayList<String>();
						for (int i = 0; i < listings.size(); i++) {
							if(listings.get(i).getCategory().equals("Bikes")) {
								titles_bikes.add(listings.get(i).getTitle());
			    				categories_bikes.add(listings.get(i).getCategory());
			    				final ListView listview = (ListView) findViewById(R.id.listview2);
			        	        final ArrayAdapter adapter;
			        		    adapter = new CustomAdapter(SearchActivity.this,titles_bikes,categories_bikes);
			        	        listview.setAdapter(adapter);
			        	        
			        	        TextView text = (TextView) findViewById(R.id.search_heading);
			        	        text.setText("Search Results - Bikes");
							}
						}
						
						if (titles_bikes.size() == 0) {
							final int duration = Toast.LENGTH_SHORT;
							Toast toast1 = Toast.makeText(getApplicationContext(), "There are no search results in the Bikes category", duration);
				        	toast1.show();
						}
					}
				});
    			
      			books.setOnClickListener(new View.OnClickListener() {
					
    					@Override
    					public void onClick(View arg0) {
    						ArrayList<String> titles_books = new ArrayList<String>();
    		    			ArrayList<String> categories_books = new ArrayList<String>();
    						for (int i = 0; i < listings.size(); i++) {
    							if(listings.get(i).getCategory().equals("Books")) {
    								titles_books.add(listings.get(i).getTitle());
    			    				categories_books.add(listings.get(i).getCategory());
    			    				final ListView listview = (ListView) findViewById(R.id.listview2);
    			        	        final ArrayAdapter adapter;
    			        		    adapter = new CustomAdapter(SearchActivity.this,titles_books,categories_books);
    			        	        listview.setAdapter(adapter);
    			        	        
    			        	        TextView text = (TextView) findViewById(R.id.search_heading);
    			        	        text.setText("Search Results - Books");
    							}
    						}
    						
    						if (titles_books.size() == 0) {
    							final int duration = Toast.LENGTH_SHORT;
    							Toast toast1 = Toast.makeText(getApplicationContext(), "There are no search results in the Books category", duration);
    				        	toast1.show();
    						}
    					}
    				});
      			
      			electronics.setOnClickListener(new View.OnClickListener() {
					
    					@Override
    					public void onClick(View arg0) {
    						ArrayList<String> titles_electronics = new ArrayList<String>();
    		    			ArrayList<String> categories_electronics = new ArrayList<String>();
    						for (int i = 0; i < listings.size(); i++) {
    							if(listings.get(i).getCategory().equals("Electronics")) {
    								titles_electronics.add(listings.get(i).getTitle());
    			    				categories_electronics.add(listings.get(i).getCategory());
    			    				final ListView listview = (ListView) findViewById(R.id.listview2);
    			        	        final ArrayAdapter adapter;
    			        		    adapter = new CustomAdapter(SearchActivity.this,titles_electronics,categories_electronics);
    			        	        listview.setAdapter(adapter);
    			        	        
    			        	        TextView text = (TextView) findViewById(R.id.search_heading);
    			        	        text.setText("Search Results - Electronics");
    							}
    						}
    						
    						if (titles_electronics.size() == 0) {
    							final int duration = Toast.LENGTH_SHORT;
    							Toast toast1 = Toast.makeText(getApplicationContext(), "There are no search results in the Electronics category", duration);
    				        	toast1.show();
    						}
    					}
    				});
      			
      			furniture.setOnClickListener(new View.OnClickListener() {
					
    					@Override
    					public void onClick(View arg0) {
    						ArrayList<String> titles_furniture = new ArrayList<String>();
    		    			ArrayList<String> categories_furniture = new ArrayList<String>();
    						for (int i = 0; i < listings.size(); i++) {
    							if(listings.get(i).getCategory().equals("Furniture")) {
    								titles_furniture.add(listings.get(i).getTitle());
    			    				categories_furniture.add(listings.get(i).getCategory());
    			    				final ListView listview = (ListView) findViewById(R.id.listview2);
    			        	        final ArrayAdapter adapter;
    			        		    adapter = new CustomAdapter(SearchActivity.this,titles_furniture,categories_furniture);
    			        	        listview.setAdapter(adapter);
    			        	        
    			        	        TextView text = (TextView) findViewById(R.id.search_heading);
    			        	        text.setText("Search Results - Furniture");
    							}
    						}
    						
    						if (titles_furniture.size() == 0) {
    							final int duration = Toast.LENGTH_SHORT;
    							Toast toast1 = Toast.makeText(getApplicationContext(), "There are no search results in the Furniture category", duration);
    				        	toast1.show();
    						}
    					}
    				});
      			
      			meetups.setOnClickListener(new View.OnClickListener() {
					
    					@Override
    					public void onClick(View arg0) {
    						ArrayList<String> titles_meetups = new ArrayList<String>();
    		    			ArrayList<String> categories_meetups = new ArrayList<String>();
    						for (int i = 0; i < listings.size(); i++) {
    							if(listings.get(i).getCategory().equals("Meetups")) {
    								titles_meetups.add(listings.get(i).getTitle());
    			    				categories_meetups.add(listings.get(i).getCategory());
    			    				final ListView listview = (ListView) findViewById(R.id.listview2);
    			        	        final ArrayAdapter adapter;
    			        		    adapter = new CustomAdapter(SearchActivity.this,titles_meetups,categories_meetups);
    			        	        listview.setAdapter(adapter);
    			        	        
    			        	        TextView text = (TextView) findViewById(R.id.search_heading);
    			        	        text.setText("Search Results - Meetups");
    							}
    						}
    						
    						if (titles_meetups.size() == 0) {
    							final int duration = Toast.LENGTH_SHORT;
    							Toast toast1 = Toast.makeText(getApplicationContext(), "There are no search results in the Meetups category", duration);
    				        	toast1.show();
    						}
    					}
    				});
      			
      			rides.setOnClickListener(new View.OnClickListener() {
					
    					@Override
    					public void onClick(View arg0) {
    						ArrayList<String> titles_rides = new ArrayList<String>();
    		    			ArrayList<String> categories_rides = new ArrayList<String>();
    						for (int i = 0; i < listings.size(); i++) {
    							if(listings.get(i).getCategory().equals("Rides")) {
    								titles_rides.add(listings.get(i).getTitle());
    			    				categories_rides.add(listings.get(i).getCategory());
    			    				final ListView listview = (ListView) findViewById(R.id.listview2);
    			        	        final ArrayAdapter adapter;
    			        		    adapter = new CustomAdapter(SearchActivity.this,titles_rides,categories_rides);
    			        	        listview.setAdapter(adapter);
    			        	        
    			        	        TextView text = (TextView) findViewById(R.id.search_heading);
    			        	        text.setText("Search Results - Rides");
    							}
    						}
    						if (titles_rides.size() == 0) {
    							final int duration = Toast.LENGTH_SHORT;
    							Toast toast1 = Toast.makeText(getApplicationContext(), "There are no search results in the Rides category", duration);
    				        	toast1.show();
    						}
    					}
    				});
      			


      			misc.setOnClickListener(new View.OnClickListener() {
					
    					@Override
    					public void onClick(View arg0) {
    						ArrayList<String> titles_misc = new ArrayList<String>();
    		    			ArrayList<String> categories_misc = new ArrayList<String>();
    						for (int i = 0; i < listings.size(); i++) {
    							if(listings.get(i).getCategory().equals("Miscellaneous")) {
    								titles_misc.add(listings.get(i).getTitle());
    			    				categories_misc.add(listings.get(i).getCategory());
    			    				final ListView listview = (ListView) findViewById(R.id.listview2);
    			        	        final ArrayAdapter adapter;
    			        		    adapter = new CustomAdapter(SearchActivity.this,titles_misc,categories_misc);
    			        	        listview.setAdapter(adapter);
    			        	        
    			        	        TextView text = (TextView) findViewById(R.id.search_heading);
    			        	        text.setText("Search Results - Miscellaneous");
    							}
    						}
    						if (titles_misc.size() == 0) {
    							final int duration = Toast.LENGTH_SHORT;
    							Toast toast1 = Toast.makeText(getApplicationContext(), "There are no search results in the Miscellaneous category", duration);
    				        	toast1.show();
    						}
    					}
    				});
      			
      			all.setOnClickListener(new View.OnClickListener() {
					
    					@Override
    					public void onClick(View arg0) {
    						ArrayList<String> titles_all = new ArrayList<String>();
    		    			ArrayList<String> categories_all = new ArrayList<String>();
    						for (int i = 0; i < listings.size(); i++) {
    								titles_all.add(listings.get(i).getTitle());
    			    				categories_all.add(listings.get(i).getCategory());
    			    				final ListView listview = (ListView) findViewById(R.id.listview2);
    			        	        final ArrayAdapter adapter;
    			        		    adapter = new CustomAdapter(SearchActivity.this,titles_all,categories_all);
    			        	        listview.setAdapter(adapter);
    			        	        
    			        	        TextView text = (TextView) findViewById(R.id.search_heading);
    			        	        text.setText("Search Results - All Categories");
    						}
    						
    						if (titles_all.size() == 0) {
    		    				final int duration = Toast.LENGTH_SHORT;
    							Toast toast1 = Toast.makeText(getApplicationContext(), "There are no search results for this search term", duration);
    				        	toast1.show();
    		    			}
    					}
    				});
    			final ListView listview = (ListView) findViewById(R.id.listview2);
    	        final ArrayAdapter adapter;
    		    adapter = new CustomAdapter(SearchActivity.this,titles,categories);
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
    	              Intent i = new Intent(SearchActivity.this, ListingActivity.class);
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
 

