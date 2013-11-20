package com.example.gregslist;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

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
		//Log.d("ALD",String.valueOf(value));
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
        String urls[] = new String[2];
        urls[0] = full_url;
        String url2 = "http://ec2-50-112-191-198.us-west-2.compute.amazonaws.com/GregsList/Android_API/account_phone.php";
        StringBuilder text2 = new StringBuilder().append(url2).append("?userID=").append(String.valueOf(value));
        String full_url_2 = text2.toString();
        urls[1] = full_url_2;
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
        	HttpGet get2 = new HttpGet(url2);
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
        	if (result2.equals("")) {
        		result2 = "No phone number";
        	}
           return result + "#" + result2;
           }

        protected void onPostExecute(String result) {
        	Log.d("ALD",result);
        	String[] separated = result.split("#");
        	String email_get = separated[0]; 
        	String phone_get = separated[1];
        	TextView email = (TextView) findViewById(R.id.email_account);
        	StringBuilder email_line = new StringBuilder().append("Email Address: ").append(email_get);
        	email.setText(email_line.toString());
        	TextView phone = (TextView) findViewById(R.id.phone);
        	StringBuilder phone_line = new StringBuilder().append("Phone Number: ").append(phone_get);
        	phone.setText(phone_line.toString());
        }
    }

}
