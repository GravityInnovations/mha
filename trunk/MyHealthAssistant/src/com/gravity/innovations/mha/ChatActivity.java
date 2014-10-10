package com.gravity.innovations.mha;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ChatActivity extends ActionBarActivity implements Common.Callbacks.HttpCallback, OnItemClickListener{
	String[] user = new String[] { "Murtaza Malik ",
			"Ayemal mir",
			"Omer",
			"Faik",
			"My Health assistant",
			"fyp"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		HttpTask Temp = new HttpTask((Activity)this, "http://mha.gravityinv.com/get_doctors.php", null, Common.HttpMethod.HttpGet, 1);
		// new HttpTask("http://mha.gravityinv.com/get_doctors.php", 1);
//		ArrayAdapter<String> user_adapter = new ArrayAdapter<String>
//		(this, android.R.layout.simple_list_item_1, user);
//		 ListView user_list = (ListView)findViewById(R.id.doctorslist);
//		 user_list.setAdapter(user_adapter);
//		 user_list.setOnItemClickListener(new OnItemClickListener() {
//
//			 public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
//			 {
//				 String name=(String)arg0.getItemAtPosition(position);
//				 Toast.makeText(getApplicationContext(), "You have chosen the user: " + " " + name, Toast.LENGTH_LONG).show();
//				 onItemzero(name);
//			 }
//			 });
//			 
		Temp.execute();
	}
	public void onItemzero(String  name) {
		Intent i  = new Intent(this , ChatInsatanceActivity.class);
		i.putExtra("username", name);
		startActivity(i);
	 
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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
	}
	@Override
	public void httpResult(JSONObject data, int RequestCode, int ResultCode) {
		ArrayList<String> users = new ArrayList<String>();
		JSONArray a;
		try {
			a = data.getJSONArray("data");
		
			for(int i=0; i< a.length();i++)
			{
				users.add(((JSONObject)a.get(i)).getString("email"));
				
			}
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayAdapter<String> user_adapter = new ArrayAdapter<String>
		(this, android.R.layout.simple_list_item_1, users);
		 ListView user_list = (ListView)findViewById(R.id.doctorslist);
		 user_list.setAdapter(user_adapter);
		 user_list.setOnItemClickListener(new OnItemClickListener() {

			 public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
			 {
				 String name=(String)arg0.getItemAtPosition(position);
				 Toast.makeText(getApplicationContext(), "You have chosen the user: " + " " + name, Toast.LENGTH_LONG).show();
				 onItemzero(name);
			 }
			 });
	}
}
