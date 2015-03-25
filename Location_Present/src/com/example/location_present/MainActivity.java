package com.example.location_present;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        int myNum=Integer.parseInt(id);
          
        Uri uri = Uri.parse("content://com.example.contentprovider/movement_info/"+id);
        String[] args = { id};
        Cursor mCursor = getContentResolver().query(uri, null, "_ID=?", args,null);
        
        ArrayList<String> ID = new ArrayList<String>();
        ArrayList<String> Timestamp = new ArrayList<String>();
        ArrayList<String> Longtitude = new ArrayList<String>();
        ArrayList<String> Latitude = new ArrayList<String>();
       
		if (mCursor.moveToFirst()) {
			do {
			   
			    ID.add(mCursor.getString(0));
			    Timestamp.add(mCursor.getString(1));
			    Longtitude.add(mCursor.getString(2));
			    Latitude.add(mCursor.getString(3));
			} while (mCursor.moveToNext());
		}
		
        
		
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" ID ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Timestamp ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Longtitude ");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Latitude ");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
        for (int i = 0; i < ID.size(); i++) {
            TableRow tbrow = new TableRow(this);
            //tbrow.setPadding(4, 4, 4, 4);
            TextView t1v = new TextView(this);
            t1v.setText("" + ID.get(i));
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText("" + Timestamp.get(i));
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText("" +  Longtitude.get(i));
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText("" + Latitude.get(i));
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
            stk.addView(tbrow);
        }
	
	
        
    }
}
