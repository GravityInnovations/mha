package com.gravity.innovation.mha.fragments;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import com.gravity.innovations.mha.ChatInsatanceActivity;
import com.gravity.innovations.mha.Common;
import com.gravity.innovations.mha.HttpTask;
import com.gravity.innovations.mha.R;
 
 


public class ChatFragmentTab extends Fragment implements  Common.Callbacks.HttpCallback, OnItemClickListener {
	String[] user = new String[] { "Murtaza Malik ",
			"Ayemal mir",
			"Omer",
			"Faik",
			"My Health assistant",
			"fyp"};
	 View rootView;
	 ListView user_list;
	 Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.chat_layout, container, false);
        //ArrayAdapter<String> user_adapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, user);
		//ArrayAdapter<String> user_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, user); 
        user_list = (ListView)rootView.findViewById(R.id.doctorslist);
        
//		 user_list.setAdapter(user_adapter);
//		 user_list.setOnItemClickListener(new OnItemClickListener() {
//
//			 public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
//			 {
//				 String name=(String)arg0.getItemAtPosition(position);
//				// Toast.makeText(getBaseContext(), "You have chosen the user: " + " " + name, Toast.LENGTH_LONG).show();
//				 onItemzero(name);
//			 }
//			 });
        
        return rootView;
    }
    public void setUp(Context mContext)
    {
    	this.mContext = mContext;
    	HttpTask Temp = new HttpTask((Activity)getActivity(), "http://mha.gravityinv.com/get_doctors.php", null, Common.HttpMethod.HttpGet, 1);
		Temp.execute();
    }
    public void onItemzero(String  name) {
    	
	}
    
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void httpResult(JSONObject data, int RequestCode, int ResultCode) {
		switch(RequestCode)
		{
		case 1:
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
			(mContext, android.R.layout.simple_list_item_1, users);
			 //ListView user_list = (ListView)findViewById(R.id.doctorslist);
			 user_list.setAdapter(user_adapter);
			 user_list.setOnItemClickListener(new OnItemClickListener() {

				 public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
				 {
					 String name=(String)arg0.getItemAtPosition(position);
					 HttpTask Temp = new HttpTask((Activity)getActivity(), "http://mha.gravityinv.com/getChat.php", null, Common.HttpMethod.HttpGet, 2);
						Temp.execute();
					 
					 
					 
					 // Toast.makeText(getApplicationContext(), "You have chosen the user: " + " " + name, Toast.LENGTH_LONG).show();
					 //onItemzero(name);
				 }
				 });
		
			break;
		case 2:
			//if response is success
			//start chat instance activity and push old data to chat
			break;
		}
	}
}