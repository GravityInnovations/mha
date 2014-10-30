package com.gravity.innovations.mha;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.internal.cn;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ChatInsatanceActivity extends ActionBarActivity implements Common.Callbacks.HttpCallback,OnClickListener {
	TextView recive_msg;
	EditText send_msg;
	Button send;
	//TextView chatwith;
	String value;
	Activity a;
	public String conversationId;
	List<NameValuePair> postData = new ArrayList<NameValuePair>();
	public SharedPreferences sp;
	public SharedPreferences.Editor spe;
	public boolean flag = false;
	JSONObject obj ;
	String [] tempdata = {"hy","rt","efed","eegte","erefefef","efefef"};
	String [] tempdata2 = {"y","t","ef///ed","dededgte","erefefffffef","ewewewefefef"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chatinstance);
		Intent i = getIntent();
		try {
			 obj = new JSONObject(getIntent().getStringExtra("chatobj"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setmsg(obj);
		// get extra data got from server > ref: chat activity http result case 2
		// get extra data got from server > ref: chat activity http result case 2
		sp = this.getSharedPreferences("MHASP", MODE_PRIVATE);
		spe = sp.edit();
		send_msg = (EditText)findViewById(R.id.sendtext);
		send = (Button)findViewById(R.id.send);
		//chatwith = (TextView)findViewById(R.id.chatwith);
		//chatwith.setText(value);
		a = (Activity)this;
		
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url = "";
				
					url = "http://mha.gravityinv.com/sendMessage.php";
				// TODO Auto-generated method stub
				postData.add(new BasicNameValuePair("reply", send_msg.getText().toString()));
				postData.add(new BasicNameValuePair("u_id", sp.getString("userid", "")));
				postData.add(new BasicNameValuePair("c_id", conversationId));
				HttpTask Temp = new HttpTask(a, url, postData,Common.HttpMethod.HttpPost,1);
				Temp.execute();
				sender_adapter.add(send_msg.getText().toString());
				sender_adapter.notifyDataSetChanged();
				send_msg.setText("");
			}
		});
		
		
		
	}
	ArrayAdapter<String> sender_adapter;
public void setmsg(JSONObject data)
{
	ArrayList<String> chatData = new ArrayList<>();
	JSONArray a = null;
	try {
		conversationId = data.getString("c_id");
		a = data.getJSONArray("data");
	
		for(int i=0; i< a.length();i++)
		{
			chatData.add(((JSONObject)a.get(i)).getString("message"));//username
			
		}
	
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	sender_adapter = new ArrayAdapter<String>
	(this, android.R.layout.simple_list_item_1, chatData);
	try{ ListView sender_list = (ListView)findViewById(R.id.chat1);
	 sender_list.setAdapter(sender_adapter);
	}
	catch(Exception ex)
	{
		String x = ex.getLocalizedMessage();
	}
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
	public void httpResult(JSONObject data, int RequestCode, int ResultCode) {
		
		}
}
