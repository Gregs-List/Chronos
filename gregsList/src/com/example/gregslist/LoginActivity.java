package com.example.gregslist;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.R.string;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
    public void sendMessage(View view) {
		Context context = getApplicationContext();
    	int duration = Toast.LENGTH_SHORT;
    	EditText email = (EditText)findViewById(R.id.email);
    	EditText password = (EditText)findViewById(R.id.password);
    	if(email.getText().toString().trim().equals(""))
    	 {   
        	Toast toast1 = Toast.makeText(context, "Enter an email address", duration);
        	toast1.show();
    	 }
    	
    	if(password.getText().toString().trim().equals(""))
   	 {   
       	Toast toast2 = Toast.makeText(context, "Enter your password", duration);
       	toast2.show();
   	 }
    	
    	//String text = new StringBuilder().append(email.getText()).append("\n").append(password.getText()).toString();
    	String url = "http://ec2-50-112-191-198.us-west-2.compute.amazonaws.com/GregsList/Android_API/login.php";
    	StringBuilder text = new StringBuilder().append(url).append("?email=").append(email.getText()).append("&password=").append(password.getText());
        String full_url = text.toString();
        Log.d("check",full_url);
        if (email.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")) {
        	//do nothing
        } else {
        	new DownloadFilesTask().execute(full_url);
        }
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
        	Context context = getApplicationContext();
        	Toast toast;
        	int duration = Toast.LENGTH_SHORT;
				if (result.contains("error")) {
		        	Log.d("ALD","Login Fail");
		           	toast = Toast.makeText(context, "Invalid login. Please try again", duration);
			        toast.show();
		           } else {
		        	   Log.d("ALD","Login Success");
		           	   Intent i = new Intent(LoginActivity.this, UserHome.class);
		        	   Bundle b = new Bundle();
		        	   b.putInt("id", Integer.parseInt(result)); //Your id
		        	   i.putExtras(b);
		        	   startActivity(i);
		           }
        }
    }
 
}