package com.gravity.innovations.mha;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterUserActivity extends ActionBarActivity implements OnClickListener, Common.Callbacks.HttpCallback{
	EditText user_name;
	EditText password;
	EditText email;
	Button signup;
	RadioButton doctor;
	String deviceid;
	public SharedPreferences sp;
	public SharedPreferences.Editor spe;
	Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getApplicationContext();
		setContentView(R.layout.activity_register);
		Intent i = getIntent();
		if (i.hasExtra("deviceid")){
	          deviceid = i.getStringExtra("deviceid");   
	    }
		sp = this.getSharedPreferences("MHASP", MODE_PRIVATE);
		spe = sp.edit();
		//doctor = (RadioButton)findViewById(R.id.isdoctor);
		user_name = (EditText)findViewById(R.id.username);
		RadioGroup isdoctor = (RadioGroup)findViewById(R.id.isdoctor);
	
		password = (EditText)findViewById(R.id.password);
		email = (EditText)findViewById(R.id.Email);
		signup = (Button)findViewById(R.id.register);
		//doctor = (RadioButton)findViewById(R.id.doctor);
		
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		 
				if (user_name.getText().toString().isEmpty() && password.getText().toString().isEmpty() && email.getText().toString().isEmpty()){
					Toast.makeText(mContext, "All Fields Empty", Toast.LENGTH_SHORT).show();
				}else if (password.getText().toString().isEmpty()){
					Toast.makeText(mContext, "Password Field is Empty", Toast.LENGTH_SHORT).show();
				}else if (user_name.getText().toString().isEmpty()){
					Toast.makeText(mContext, "Username Field is Empty", Toast.LENGTH_SHORT).show();
				}else if (email.getText().toString().isEmpty()){
					Toast.makeText(mContext, "Email Field is Empty", Toast.LENGTH_SHORT).show();
				}else{
					register( );
				}
			}
		});
		
	}
	public void register( ){
		List<NameValuePair> postData = new ArrayList<NameValuePair>();
		postData.add(new BasicNameValuePair("username",   user_name.getText().toString()/**/ ));
		postData.add(new BasicNameValuePair("password",     password.getText().toString()/**/));
		postData.add(new BasicNameValuePair("email",     email.getText().toString() /**/));
//		String isDoc = doctor.getText().toString();
		
//		if(isDoc.toLowerCase() == "doctor")
//		postData.add(new BasicNameValuePair("isdoctor", "1" ));
//		else 
		postData.add(new BasicNameValuePair("isdoctor", "0" ));
		postData.add(new BasicNameValuePair("device_id", deviceid));
//		//add or edit
//		if(tasklist.gravity_id != ""){
//			postData.add(new BasicNameValuePair("_id", tasklist.gravity_id));
//			postData.add(new BasicNameValuePair("action", "edit"));
//		}
//		else
//			postData.add(new BasicNameValuePair("action", "add"));
//		postData.add(new BasicNameValuePair("title", tasklist.title));
		HttpTask Temp = new HttpTask(this, "http://mha.gravityinv.com/signup.php", postData, Common.HttpMethod.HttpPost,
				1);
		Temp.execute();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void httpResult(JSONObject data, int RequestCode, int ResultCode) {
		if (ResultCode == Common.HTTP_RESPONSE_OK){
		spe.putBoolean("RegComp", true);
		//save userid, username and email in prefs
		try {
			spe.putString("userid", data.getString("userId"));
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
			Toast.makeText(getApplicationContext(), "Server is Down", Toast.LENGTH_SHORT).show();
			}
		}
	
}
