package com.gravity.innovation.mha.fragments;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;

import com.gravity.innovations.mha.R;
 
public class HeartrateFragmentTab extends Fragment {
	Context mContext;
	int HR=102;
	String s="03445008891";
	String m="patient is critical";
	NotificationManager mNotificationManager;
	public HeartrateFragmentTab() {
		// TODO Auto-generated constructor stub
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.heart_layout, container, false);
        mContext = container.getContext();
        sendNotification(m, mContext);
        return rootView;
    }
	//public void getcontext(Context mcontext){
		//mcontext=mcontext;
	//}
	
    public void sendSMS(String phoneNumber, String message){
    	 SmsManager sms = SmsManager.getDefault();
         sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
    
    
    public void sendEmail(Context mContext){
    	Intent intent = new Intent(Intent.ACTION_SEND);
    	intent.setType("message/rfc822");
    	intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
    	intent.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
    	intent.putExtra(Intent.EXTRA_TEXT   , "body of email");
    	try {
    	    startActivity(Intent.createChooser(intent, "Send mail..."));
    	} catch (android.content.ActivityNotFoundException ex) {
    	    Toast.makeText(mContext, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
    	}
    	
    }
    
    	
    	public void  sendNotification(String msg,Context mcontext) {
    		 int NOTIFICATION_ID =1;
    		mNotificationManager = (NotificationManager) mContext
    				
    				.getSystemService(Context.NOTIFICATION_SERVICE);
    		PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
    				new Intent(mContext, HeartrateFragmentTab.class), 0);
    		// use the right class it should be called from the where alarms are set
    		
    		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
    				mContext).setSmallIcon(R.drawable.ic_launcher)
    				.setContentTitle("Alarm Alert")
    				.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
    				.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE) 
    				.setContentText(msg);
    		
    		mBuilder.setContentIntent(contentIntent);
    		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    	
    	
    	}
    	

public void sensorthreshold(){
	
	
	
	if(HR>=100)
	{
		
		sendSMS(s, m);
		sendNotification(m,mContext);
		sendEmail(mContext);
	}
}

    	
 }
    	