package com.gravity.innovations.mha;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.sax.StartElementListener;
import android.util.Log;

public class GCMHelper extends AsyncTask<Void,Void,Void>
{
	public Context mContext;
    public String regid;
    public GoogleCloudMessaging gcm;
    public String SENDER_ID = "675714202196";
    public SharedPreferences getGCMId;
    public Editor editor;
    public static final String PROPERTY_REG_ID = "GCMDeviceId";
    public SplashActivity ac;
    //constructor....
    
    public GCMHelper(Context context, SplashActivity a)
    {
    	ac = a;
    	this.mContext=context;
    	getGCMId =mContext.getSharedPreferences("MHASP", 0); // 0 - for private mode
    	
    }//end of constructor....

	@Override
	protected Void doInBackground(Void... params) 
	{
		
		editor = getGCMId.edit();
		gcm = GoogleCloudMessaging.getInstance(mContext);
		String msg = "";
		try 
		{
            if (gcm == null) 
            {
                gcm = GoogleCloudMessaging.getInstance(mContext);
            }
            regid = gcm.register(SENDER_ID);
            msg = "Dvice registered, registration ID=" + regid;
            Log.d("111", msg);
            editor.putString(PROPERTY_REG_ID, regid);
            getGCMId.getString(PROPERTY_REG_ID, null);
            editor.commit();
            sendRegistrationIdToBackend(regid);
          

        }//end of try block..... 
		catch (IOException ex) 
        {
            msg = "Error :" + ex.getMessage();
        }
		
		return null;
	}//end of doInBackground method.....

	@Override
	protected void onPostExecute(Void result) 
	{
		//super.onPostExecute(result);
		ac.loadAll();
		//((SplashActivity)mContext).loadAll();
		//GCMHelper.
	}//End of onPostExecute method....
	
	private void sendRegistrationIdToBackend(String regid) 
	{

		String url = "http://mha.gravityinv.com/signup.php?";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("regid", regid));
       DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         

	}//End of method sendRegistrationIdToBackend.....

}//end of class GCMHelper

