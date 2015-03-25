package com.example.location;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;



import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class GPSDataCollectorService extends Service {

	private LocationManager myLocationManager;
	private LocationListener myLocationListener;
	Long tsLong;
	int count=0;
	String ts,myLatitude, myLongitude;
	Uri uri = Uri.parse("content://com.example.contentprovider/movement_info");

	Timer T=new Timer();
	private static final int HANDLER_DELAY = 1000*60*5;	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	  @Override
	  public void onDestroy() {
		  	  
		  if(myLocationManager!=null){
			  myLocationManager.removeUpdates(myLocationListener);
			  
		  }
		 
	          Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	
	   
	
	      }

	@Override
    public void onCreate() {

		myLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE); //location manager
		myLocationListener = new MyLocationListener();
		 if (!myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
	            Toast.makeText(getApplicationContext(), "GPS is disabled.", Toast.LENGTH_SHORT).show();
		  } 
		 myLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,10, myLocationListener);
		 
		    //SimpleDateFormat df = new SimpleDateFormat("HH:mm dd/MMM/yy");
			//c.getTime();//current timestamp
			
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
		        public void run() {
		        	
		        	ContentValues values = new ContentValues();
		        	tsLong = System.currentTimeMillis()/1000;	// timestampt
					ts = tsLong.toString();
		        	myLatitude= String.valueOf(myLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude()); //vazei sta textfields to current latitude kai longitude
					myLongitude=String.valueOf(myLocationManager.getLastKnownLocation( LocationManager.GPS_PROVIDER).getLongitude());
					Toast.makeText(getApplicationContext(),"latitude1: "+myLatitude+" longtitude1: "+myLongitude, Toast.LENGTH_SHORT).show();	
					values.put("_LONGITUDE", myLongitude);
					values.put("_LATITUDE", myLatitude);
					values.put("_TIMESTAMP", ts);
					Uri resultUri = getContentResolver().insert(uri, values); 
		            handler.postDelayed(this, HANDLER_DELAY);
		        }
		    },HANDLER_DELAY);
		
		
		
	}
	private class MyLocationListener implements LocationListener{

		public void onLocationChanged(Location location) { //otan allazoun oi sudetagmenes kaleite i sunarthsh
		// TODO Auto-generated method stub
			ContentValues values = new ContentValues();
			tsLong = System.currentTimeMillis()/1000;	// timestampt
			ts = tsLong.toString();
			double latitude= location.getLatitude();
			double lontitude=location.getLongitude();
			String lat = String.valueOf(latitude);
			String lon = String.valueOf(lontitude);
			values.put("_LONGITUDE", lon);
			values.put("_LATITUDE", lat);
			values.put("_TIMESTAMP", ts);
			Uri resultUri = getContentResolver().insert(uri, values); 
			Toast.makeText(getApplicationContext(),"latitude: "+latitude+" logtitude: "+lontitude, Toast.LENGTH_SHORT).show();	
			
			
		}
		
		public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		}
		
		public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		}
		
		public void onStatusChanged(String provider,
		 int status, Bundle extras) {
		// TODO Auto-generated method stub
		}
		}; 

    
    
    
}

