package com.example.gregslist;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
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
    	EditText email = (EditText)findViewById(R.id.email);
    	EditText password = (EditText)findViewById(R.id.password);
    	String text = new StringBuilder().append(email.getText()).append("\n").append(password.getText()).toString();
    	int duration = Toast.LENGTH_SHORT;
    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    	/*
    	HttpClient httpClient = new DefaultHttpClient();  
    	String url = "http://lyle.smu.edu/~adoyle/3345/a7/images.json";
    	HttpGet httpGet = new HttpGet(url);
    	try {
    	    HttpResponse response = httpClient.execute(httpGet);
    	    StatusLine statusLine = response.getStatusLine();
    	    if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
    	        HttpEntity entity = response.getEntity();
    	        ByteArrayOutputStream out = new ByteArrayOutputStream();
    	        entity.writeTo(out);
    	        out.close();
    	        String responseStr = out.toString();
    	        Log.d("response",responseStr);
    	        // do something with response 
    	    } else {
    	        // handle bad response
    	    }
    	} catch (ClientProtocolException e) {
    	    // handle exception
    	} catch (IOException e) {
    	    // handle exception
    	}*/
}
}
