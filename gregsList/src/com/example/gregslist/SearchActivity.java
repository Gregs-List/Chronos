package com.example.gregslist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		Bundle b = getIntent().getExtras();
		final int value = b.getInt("id");
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

}
