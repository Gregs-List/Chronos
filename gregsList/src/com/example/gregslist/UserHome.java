package com.example.gregslist;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
		
		
		TextView id = (TextView) findViewById(R.id.user_activity);
		id.setText(String.valueOf(value));
		
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

}
