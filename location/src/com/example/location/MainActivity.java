package com.example.location;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;


import android.app.Activity;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private LocationManager myLocationManager;
	private LocationListener myLocationListener;
	Button  mButton,show;
	Long tsLong;
	int count=0;
	String ts,myLatitude, myLongitude;
	Timer T=new Timer();
	private static final int HANDLER_DELAY = 1000*60*5;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       

        show=(Button) findViewById(R.id.button2);
       
	    mButton = (Button) findViewById(R.id.button1);
	    mButton.setTag(1);
	   
		  mButton.setOnClickListener(
			        new View.OnClickListener()
			        {
			            public void onClick(View view)
			            { 
			            	
			            	
			            	final int status =(Integer) view.getTag();
			            	if(status == 0) {
			            		startService(new Intent(MainActivity.this , GPSDataCollectorService.class));
			            		mButton.setText("Stop");
			            	    view.setTag(1); //start
			            	} else {
			            		mButton.setText("Start");
			            		stopService(new Intent(MainActivity.this , GPSDataCollectorService.class));
			            	    view.setTag(0); //pause
			            	} 
			            	
			            	
			            	//startService(new Intent(MainActivity.this , GPSDataCollectorService.class));
			            	//stopService(new Intent(MainActivity.this , GPSDataCollectorService.class));
		            
			            }
			            
			        });
		  
		  show.setOnClickListener(
			        new View.OnClickListener()
			        {
			            public void onClick(View view)
			            { 
            	
			            	//explicit intent
			            	final EditText  mEdit   = (EditText)findViewById(R.id.editText1);
			            	String id= mEdit.getText().toString();
			        	    Intent i =new Intent();
	        			    i.setClassName("com.example.location_present", "com.example.location_present.MainActivity");
	        			    i.putExtra("id",id); 
	        			    startActivity(i);

			            }
			            
			        });
		  
		  
		  
		  
    }
    

    
}
