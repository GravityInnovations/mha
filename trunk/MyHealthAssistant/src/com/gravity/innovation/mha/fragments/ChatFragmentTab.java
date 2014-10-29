package com.gravity.innovation.mha.fragments;


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
import com.gravity.innovations.mha.R;
 
 


public class ChatFragmentTab extends Fragment implements OnItemClickListener {
	String[] user = new String[] { "Murtaza Malik ",
			"Ayemal mir",
			"Omer",
			"Faik",
			"My Health assistant",
			"fyp"};
	 View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.chat_layout, container, false);
        //ArrayAdapter<String> user_adapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, user);
		ArrayAdapter<String> user_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, user); 
        ListView user_list = (ListView)rootView.findViewById(R.id.doctorslist);
		 user_list.setAdapter(user_adapter);
		 user_list.setOnItemClickListener(new OnItemClickListener() {

			 public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
			 {
				 String name=(String)arg0.getItemAtPosition(position);
				// Toast.makeText(getBaseContext(), "You have chosen the user: " + " " + name, Toast.LENGTH_LONG).show();
				 onItemzero(name);
			 }
			 });
        
        return rootView;
    }
    public void onItemzero(String  name) {
    	
	}
    
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}