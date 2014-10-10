package com.gravity.innovations.mha;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChatInsatanceActivity extends ActionBarActivity implements OnClickListener {
	TextView recive_msg;
	EditText send_msg;
	Button send;
	//TextView chatwith;
	String value;
	Activity a;
	List<NameValuePair> postData = new ArrayList<NameValuePair>();
	public SharedPreferences sp;
	public SharedPreferences.Editor spe;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chatinstance);
		Intent i = getIntent();
		if (i.hasExtra("username")){
	          value = i.getStringExtra("username");   
	    }
		
		sp = this.getSharedPreferences("MHASP", MODE_PRIVATE);
		spe = sp.edit();
		recive_msg = (TextView)findViewById(R.id.recivemsg); 
		send_msg = (EditText)findViewById(R.id.sendtext);
		send = (Button)findViewById(R.id.send);
		//chatwith = (TextView)findViewById(R.id.chatwith);
		//chatwith.setText(value);
		if (i.hasExtra("message")){
			recive_msg.setText(i.getStringExtra("message").toString());
		}
		
		a = (Activity)this;
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				postData.add(new BasicNameValuePair("reply", send_msg.getText().toString()));
				postData.add(new BasicNameValuePair("name_sender", sp.getString(Common.USER_NAME, "")));
				HttpTask Temp = new HttpTask(a, "http://mha.gravityinv.com/insertingrply.php", postData,Common.HttpMethod.HttpPost,1);
				Temp.execute();
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
