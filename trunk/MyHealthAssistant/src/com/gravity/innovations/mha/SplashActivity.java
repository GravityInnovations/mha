package com.gravity.innovations.mha;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends Activity{
	public SharedPreferences sp;
	public SharedPreferences.Editor spe;
	public String GCMId;
	public Boolean RegComp;
	private Context context;
	public TextView Loading_text;
    public static final String PROPERTY_REG_ID = "GCMDeviceId";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splash);
		context=getApplicationContext();
		Loading_text = (TextView)findViewById(R.id.load);
		loadAll();
		//isGoogleServicesAvailable();
	}
	
	public void loadAll()
	{
		sp = this.getSharedPreferences("MHASP", MODE_PRIVATE);
		spe = sp.edit();
		Loading_text.setText("Checking Internet Coonection");
		if(hasInternet(this))
		{
			Loading_text.setText(sp.getString(PROPERTY_REG_ID, ""));
			Loading_text.setText("Checking Google Registration");
			if(sp.getString(PROPERTY_REG_ID, "") != "")
			{
				Loading_text.setText("Checking user registration");
				if(sp.getBoolean("RegComp", false))
				{
					
					Continue();
				}
				else
				{
					try{
					Intent intent = new Intent(SplashActivity.this,SignInActivity.class);
					intent.putExtra("deviceid", sp.getString(PROPERTY_REG_ID, ""));
			         startActivity(intent);
			         finish();
					}
					catch(Exception ex){
						Loading_text.setText(ex.getLocalizedMessage());
					}
				}
			}
			else{
				//this is temp if not play services
				spe.putString(PROPERTY_REG_ID, "ggygygygyuuhu");
				spe.commit();
				loadAll();
				//orignal
				GCMHelper h=new GCMHelper(context, this);
				//h.execute();
				
			}
		}
		else{
			Loading_text.setText("no internet");
			//make a toast and exit after 3 seconds
		}
	}
	
	public void Continue()
	{
		 Intent intent = new Intent(this,MainActivity.class);
         startActivity(intent);
         finish();
	}
	
	//Check google play services.....
	
    public boolean isGoogleServicesAvailable()
    {
    	   int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    	   TextView googlePlayServices = (TextView) findViewById(R.id.tvGooglePlayServices);
    	   if(result == ConnectionResult.SUCCESS)
    	   {
    	      //Google Play services installed, enabled and available
    	      googlePlayServices.setText("Google Play Services is available");
    	      return true;
    	    }
    	    else
    	    {
    	       //Google Play Services not available, show dialog with further instructions
    	       googlePlayServices.setText("Google Play Services is not enabled or installed, this is Required for this demo. Please install or update Google Play services from the Play Store.");
    	       return false;
    	    }
    	 }//End of is GoogleServiceAvailable Method.....
    
    //Check Device is connected to Internet.....
    public boolean hasInternet(Activity mActivity) {

		ConnectivityManager cm = (ConnectivityManager) mActivity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo i = cm.getActiveNetworkInfo();
		if (i == null)
			return false;
		if (!i.isConnected())
			return false;
		if (!i.isAvailable())
			return false;
		return true;

	}
    public boolean isDeviceConnected()
    {
    	   ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    	   NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    	   TextView networkConnection = (TextView) findViewById(R.id.tvNetworkConnection);
    	   if (networkInfo != null && networkInfo.isConnected())
    	   {
    	      networkConnection.setText("Internet Connection is available");
    	      return true;
    	   } 
    	      else 
    	      {
    	         networkConnection.setText("No Internet Connection is available, please ensure that Wifi or Mobile Data is turned on");
    	         return false;
    	      }
    }//End of isDeviceConnected Method......


	
	

}
