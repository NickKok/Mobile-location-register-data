package com.example.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	//database and tables
	private static final String DATABASE_NAME = "exercise";
    public static final String MOVEMENTS_TABLE = "movement_info";
    private static final int DATABASE_VERSION = 1; //v3 to use current_timestamp in sqlite db

    
	
	//movements table columns
	private static final String MOV_ID = "_ID";
	private static final String MOV_TIMESTAMP = "_TIMESTAMP";
	private static final String MOV_LONG = "_LONGITUDE";
	private static final String MOV_LAT = "_LATITUDE";


    
    //create query for movements table
    private static final String  CREATE_MOVEMENTS_TABLE = "CREATE TABLE " + MOVEMENTS_TABLE +"("+ MOV_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + MOV_TIMESTAMP + " TEXT NOT NULL, " +MOV_LONG + " TEXT NOT NULL, " + MOV_LAT + " TEXT NOT NULL " + ");";
	
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_MOVEMENTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}

