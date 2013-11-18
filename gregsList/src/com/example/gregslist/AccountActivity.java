package com.example.gregslist;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.gregslist.LoginActivity.DownloadFilesTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		Bundle b = getIntent().getExtras();
		final int value = b.getInt("id");
		//TextView id = (TextView) findViewById(R.id.user_id);
		//id.setText(String.valueOf(value));
		
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
		
		String url = "http://ec2-50-112-191-198.us-west-2.compute.amazonaws.com/GregsList/Android_API/account_email.php";
    	StringBuilder text = new StringBuilder().append(url).append("?userID=").append(String.valueOf(value));
        String full_url = text.toString();
		new DownloadFilesTask().execute(full_url);
		
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
    		String result = " ";
            Log.d("check","doInBackground has been called");
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
        	TextView email = (TextView) findViewById(R.id.email_account);
        	StringBuilder email_line = new StringBuilder().append("Email Address: ").append(result);
        	email.setText(email_line.toString());
        }
    }

}
