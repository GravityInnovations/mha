package com.gravity.innovations.mha;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SearchFragmentTab extends Fragment implements OnClickListener{
TextView search;
ListView result;
View rootView;
Button ok;
String[] user = new String[] { "Murtaza Malik ",
		"Ayemal mir",
		"Omer",
		"Faik",
		"My Health assistant",
		"fyp"};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		  rootView = inflater.inflate(R.layout.search_layout, container, false);
	      search = (TextView) rootView.findViewById(R.id.search);
	      ok = (Button)rootView.findViewById(R.id.ok);
	      ArrayAdapter<String> user_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, user); 
	        ListView user_list = (ListView)rootView.findViewById(R.id.result);
			 user_list.setAdapter(user_adapter);
			 
			 ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
			 
		
		
		return rootView;
}
	
public void onItemzero(String  name) {
	
	}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	
}
   

}
