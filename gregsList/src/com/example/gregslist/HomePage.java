package com.example.gregslist;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomePage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		
	Button login = (Button) findViewById(R.id.login);
	login.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent(HomePage.this, LoginActivity.class);
			startActivity(i);
		}
	});
	
	ActionBar actionbar = getActionBar();
	actionbar.hide();
	
	Button signup = (Button) findViewById(R.id.signup);
	signup.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent j = new Intent(HomePage.this, SignUp.class);
			startActivity(j);
			
		}
	});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
	}

}
