package com.gravity.innovations.mha;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends ActionBarActivity implements  Common.Callbacks.HttpCallback{
	String deviceid;
	EditText user_name;
	EditText password;
	Button signup;
	Button signin;
	public SharedPreferences sp;
	public SharedPreferences.Editor spe;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		Intent i = getIntent();
		sp = this.getSharedPreferences("MHASP", MODE_PRIVATE);
		spe = sp.edit();
		if (i.hasExtra("deviceid")){
	          deviceid = i.getStringExtra("deviceid");   
	    }
		user_name = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		signup = (Button)findViewById(R.id.signup);
		signin = (Button)findViewById(R.id.signin);
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				signup();
			}
		});
		signin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				signin();
			}
		});

	}
	
	public void signin(){
		List<NameValuePair> postData = new ArrayList<NameValuePair>();
		postData.add(new BasicNameValuePair("username", user_name.getText().toString()));
		postData.add(new BasicNameValuePair("password", password.getText().toString()));
	//	postData.add(new BasicNameValuePair("device_id", deviceid));
		HttpTask Temp = new HttpTask(this, "http://mha.gravityinv.com/signin.php", postData,Common.HttpMethod.HttpPost,
				1);
		Temp.execute();
	}
	
	public void signup(){
		Intent intent = new Intent(this,RegisterUserActivity.class);
		intent.putExtra("deviceid", deviceid );
         startActivity(intent);
	}
	
	@Override
	public void httpResult(JSONObject data, int RequestCode, int ResultCode) {
		// TODO Auto-generated method stub
		if (RequestCode == 1 && ResultCode==Common.HTTP_RESPONSE_OK){
			spe.putBoolean("RegComp", true);
			//same as reg with sp
			try {
				String userId = data.getString("userId");
				spe.putString("userid", userId );
				spe.putString("email", data.getString("email"));
				spe.putString("username", data.getString("username"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			spe.commit();
			Intent intent = new Intent(this,MainActivity.class);
	        startActivity(intent);
	        finish();
		}
			else {
				Toast.makeText(getApplicationContext(), "Server is Down or invalid username or password", Toast.LENGTH_SHORT).show();
				}
		}
		
	


}
