package com.gravity.innovations.mha;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.R;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout);
		Intent i = getIntent();
		if (i.hasExtra("deviceid")){
	          deviceid = i.getStringExtra("deviceid");   
	    }
		sp = this.getSharedPreferences("MHASP", MODE_PRIVATE);
		spe = sp.edit();
//		user_name = (EditText)findViewById(R.id.username);
//		password = (EditText)findViewById(R.id.password);
//		email = (EditText)findViewById(R.id.Email);
//		signup = (Button)findViewById(R.id.register);
//		doctor = (RadioButton)findViewById(R.id.doctor);
		
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				register();
			}
		});
		
	}
	public void register(){
		List<NameValuePair> postData = new ArrayList<NameValuePair>();
		postData.add(new BasicNameValuePair("name", user_name.getText().toString()));
		postData.add(new BasicNameValuePair("password", password.getText().toString()));
		postData.add(new BasicNameValuePair("email", email.getText().toString()));
		//RadioGroup isdoctor = (RadioGroup)findViewById(r);
		//postData.add(new BasicNameValuePair("isdoctor", doctor.getText().toString()));
		postData.add(new BasicNameValuePair("device_id", deviceid));
//		//add or edit
//		if(tasklist.gravity_id != ""){
//			postData.add(new BasicNameValuePair("_id", tasklist.gravity_id));
//			postData.add(new BasicNameValuePair("action", "edit"));
//		}
//		else
//			postData.add(new BasicNameValuePair("action", "add"));
//		postData.add(new BasicNameValuePair("title", tasklist.title));
		HttpTask Temp = new HttpTask(this, "http://mha.gravityinv.com/signup.php", postData,Common.HttpMethod.HttpPost,
				1);
		Temp.execute();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void httpResult(JSONObject data, int RequestCode, int ResultCode) {
		if (RequestCode == Common.HTTP_RESPONSE_OK){
		spe.putBoolean("RegComp", true);
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
