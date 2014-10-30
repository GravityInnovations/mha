package com.gravity.innovation.mha.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.gravity.innovations.mha.Pedometer;
import com.gravity.innovations.mha.R;
 
public class PedoFragmentTab extends Fragment {
	
	  @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.pedo_layout, container, false);
	        Intent intent = new Intent(getActivity(), Pedometer.class);
	       // startActivity(intent);
	        return rootView;
	    }

	
}
