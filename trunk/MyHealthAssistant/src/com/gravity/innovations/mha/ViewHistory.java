package com.gravity.innovations.mha;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
public class ViewHistory extends Activity implements Common.Callbacks.HttpCallback {
	public SharedPreferences sp;
	public SharedPreferences.Editor spe;
	TextView name;
	TextView time;
	TextView date;
	TextView bloodpressure;
	TextView heartrate;
	TextView speed;
	TextView calories;
	TextView distance;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sp = this.getSharedPreferences("MHASP", MODE_PRIVATE);
		spe = sp.edit();
		setContentView(R.layout.activity_history);
		name = (TextView)findViewById(R.id.name);
		time = (TextView)findViewById(R.id.time);
		date = (TextView)findViewById(R.id.date);
		bloodpressure = (TextView)findViewById(R.id.bloodpressure);
		heartrate = (TextView)findViewById(R.id.heartrate);
		speed = (TextView)findViewById(R.id.speed);
		calories = (TextView)findViewById(R.id.calories);
		distance = (TextView)findViewById(R.id.distance);
		List<NameValuePair> postData = new ArrayList<NameValuePair>();
		postData.add(new BasicNameValuePair("username", sp.getString("USER_NAME","")));
		
		HttpTask Temp = new HttpTask((Activity)this, "http://mha.gravityinv.com/displayhistory.php", postData, Common.HttpMethod.HttpPost,1);
		Temp.execute();
	}

	@Override
	public void httpResult(JSONObject data, int RequestCode, int ResultCode) {
		// TODO Auto-generated method stub
	//	ArrayList<String> users = new ArrayList<String>();
		JSONArray a;
		try {
			a = data.getJSONArray("data");
		
			for(int i=0; i< a.length();i++)
			{
				time.setText(data.getString("time"));
				date.setText(data.getString("date"));
				bloodpressure.setText(data.getString("BP"));
				heartrate.setText(data.getString("HR"));
				speed.setText(data.getString("speed"));
				calories.setText(data.getString("calories"));
				distance.setText(data.getString("distance"));


				
				
				//users.add(((JSONObject)a.get(i)).getString("email"));
				
				
			}
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
