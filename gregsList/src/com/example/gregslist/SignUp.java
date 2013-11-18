package com.example.gregslist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
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
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		final EditText fname = (EditText) findViewById(R.id.fname);
		final EditText lname = (EditText) findViewById(R.id.lname);
		final EditText email = (EditText) findViewById(R.id.SMU_email);
		final EditText password = (EditText) findViewById(R.id.password_signup);
		final EditText password2 = (EditText) findViewById(R.id.password_signup_two);
    	final int duration = Toast.LENGTH_SHORT;
		final Context context = getApplicationContext();
		
		final Button sign_in = (Button) findViewById(R.id.sign_up_button);
		
		sign_in.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int check = 0;
				if (fname.getText().toString().trim().equals("")) {
			    	Toast toast1 = Toast.makeText(context, "Fill in the First Name field", duration);
		        	toast1.show();
		        	check = 1;
				}
				
				if (lname.getText().toString().trim().equals("")) {
			    	Toast toast2 = Toast.makeText(context, "Fill in the Last Name field", duration);
		        	toast2.show();
		        	check = 1;
				}
				
				if (email.getText().toString().trim().equals("")) {
			    	Toast toast3 = Toast.makeText(context, "Fill in the SMU Email field", duration);
		        	toast3.show();
		        	check = 1;
				}
				
				if (password.getText().toString().trim().equals("") || password.getText().toString().length() < 8) {
			    	Toast toast4 = Toast.makeText(context, "Your password must contain at least 8 characters", duration);
		        	toast4.show();
		        	check = 1;
				}
				
				if (!password.getText().toString().equals(password2.getText().toString())) {
					Toast toast5 = Toast.makeText(context, "Passwords must match", duration);
		        	toast5.show();
		        	check = 1;
				}
				
				if (check == 0) {
				
					//Toast toast5 = Toast.makeText(context, "Registration Successful", duration);
		        	//toast5.show();
		        	new DownloadFilesTask().execute("http://ec2-50-112-191-198.us-west-2.compute.amazonaws.com/GregsList/Android_API/register.php");
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}
	

    private class DownloadFilesTask extends AsyncTask<String, Void, String> {
        
    	protected String doInBackground(String... urls) {
    		Log.d("ALD","Made it to async task");
    		EditText fname = (EditText) findViewById(R.id.fname);
    		String first = fname.getText().toString();
    		EditText lname = (EditText) findViewById(R.id.lname);
    		String last = lname.getText().toString();
    		EditText email = (EditText) findViewById(R.id.SMU_email);
    		String email_text = email.getText().toString();
    		EditText password = (EditText) findViewById(R.id.password_signup);
    		String pw = password.getText().toString();
    		String url = urls[0];
    		String result = " ";
            Log.d("ALD","doInBackground has been called");
        	HttpClient client = new DefaultHttpClient();
        	HttpPost post = new HttpPost(url);
        	try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("fname",first));
                nameValuePairs.add(new BasicNameValuePair("lname", last));
                nameValuePairs.add(new BasicNameValuePair("email", email_text));
                nameValuePairs.add(new BasicNameValuePair("password", pw));
                UrlEncodedFormEntity ent = new UrlEncodedFormEntity(nameValuePairs,HTTP.UTF_8);
                post.setEntity(ent);

                HttpResponse response = client.execute(post);
                HttpEntity entity = response.getEntity();
    			if (null != entity) {
    				result = EntityUtils.toString(entity); 
    				Log.d("ALD",result);
    			}   
            } catch (ClientProtocolException e) {
                Log.d("ALD","caught1");
            } catch (IOException e) {
                Log.d("ALD","caught2");
            }
           return result;
        }

        protected void onPostExecute(String result) {
        	int duration = Toast.LENGTH_SHORT;
        	Context context = getApplicationContext();
        	if (result.equals("error")) {
        		Toast toast = Toast.makeText(context, "This email address is already registered. Go back to login", duration);
	        	toast.show();         			
        		} else {
        			Toast toast1 = Toast.makeText(context, "Registration successful. Please login.", duration);
    	        	toast1.show();	
    	        	Intent i = new Intent(SignUp.this, LoginActivity.class);
    	        	startActivity(i);
        		}
        }
    }

}
