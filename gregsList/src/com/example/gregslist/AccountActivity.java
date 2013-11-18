package com.example.gregslist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		Bundle b = getIntent().getExtras();
		final int value = b.getInt("id");
		TextView id = (TextView) findViewById(R.id.user_id);
		id.setText(String.valueOf(value));
		
		Button logout = (Button) findViewById(R.id.logout);
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
		return true;
	}

}
