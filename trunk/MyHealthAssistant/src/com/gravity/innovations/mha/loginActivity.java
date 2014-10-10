package com.gravity.innovations.mha;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends ActionBarActivity implements OnClickListener {
	EditText user_name;
	EditText password;
	Button login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		user_name = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		login = (Button)findViewById(R.id.login);
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login();
			}
		});
		
	}
	
	public void login(){
		
	}
	
	
	
	@Override	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
