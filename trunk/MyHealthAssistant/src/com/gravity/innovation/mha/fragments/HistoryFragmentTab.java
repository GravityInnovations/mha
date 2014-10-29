package com.gravity.innovation.mha.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.google.android.gms.maps.internal.m;
import com.gravity.innovations.mha.Common;
import com.gravity.innovations.mha.HttpTask;
import com.gravity.innovations.mha.R;
 
public class HistoryFragmentTab extends Fragment implements Common.Callbacks.HttpCallback {
    TextView distance;
    TextView name;
    TextView heartrate;
    TextView bloodpressure;
    TextView speed;
    TextView calories;
    TextView time;
    Activity mActivity;
    public void SetUp(Activity mActivity)
    {
    	this.mActivity = mActivity;
    	
        
    }
    public void fetchData()
    {
    	
    }
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.history_layout, container, false);
        distance = (TextView)rootView.findViewById(R.id.distance);
        name = (TextView)rootView.findViewById(R.id.name);
        heartrate = (TextView)rootView.findViewById(R.id.heartrate);
        bloodpressure = (TextView)rootView.findViewById(R.id.bloodpressure);
        speed = (TextView)rootView.findViewById(R.id.speed);
        calories = (TextView)rootView.findViewById(R.id.calories);
        time = (TextView)rootView.findViewById(R.id.time);
       // HttpTask Temp = new HttpTask(mActivity, "http://mha.gravityinv.com/displayhistory.php?username=username",null,Common.HttpMethod.HttpGet,
				//1);
	//	Temp.execute();
        return rootView;
        
       
        
    }
    
    public void pushdata(JSONObject data)
    {
    	if (data!=null)
    	{
    		JSONArray rec;
    		try{
    			rec=data.getJSONArray("data");
    			for (int i=0;i<=rec.length();i++)
    			{
    				distance.setText(data.getString("distance"));
    				heartrate.setText(data.getString("HR"));
    				bloodpressure.setText(data.getString("BP"));
    				name.setText(data.getString("name"));
    				calories.setText(data.getString("calories"));
    				speed.setText(data.getString("speed"));
    				time.setText(data.getString("time"));
    				
    			}
    			
    		}
    		catch(JSONException e){
    			e.printStackTrace();
    		}
    	}
    	else
    		;//v.setText("data not recieved");
    }

	@Override
	public void httpResult(JSONObject data, int RequestCode, int ResultCode) {
		
		// TODO Auto-generated method stub
		
		
	}
}
		
	
