package com.example.contentprovider;



import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


public class Provider extends ContentProvider {

	private static final String AUTHORITY = "com.example.contentprovider";
	private static final String PATH_M = "movement_info";
	
	private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	static{
	
		sUriMatcher.addURI(AUTHORITY, PATH_M, 1);
		sUriMatcher.addURI(AUTHORITY, PATH_M+"/#", 2);
	}

	DBHelper mDBHelper;
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		return 0;
	}
	
	@Override
	public String getType(Uri uri) {
		return null;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) throws IllegalArgumentException {
		long id = -1;
		SQLiteDatabase mDB = mDBHelper.getWritableDatabase();
		switch(sUriMatcher.match(uri)){
		case 1:
			id = mDB.insert(DBHelper.MOVEMENTS_TABLE, null, values);
			break;
		
		default:
			throw new IllegalArgumentException ("Content URI pattern not recognizable: "+uri);
		}
		return Uri.parse(uri.toString()+"/"+id);
	}

	@Override
	public boolean onCreate() {
		mDBHelper = new DBHelper(this.getContext()); 
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) throws IllegalArgumentException {
		SQLiteDatabase mDB = mDBHelper.getReadableDatabase();
		Cursor mCursor;
		switch(sUriMatcher.match(uri)){
		case 1:
			mCursor = mDB.query(DBHelper.MOVEMENTS_TABLE, null, null, null, null, null, null);
			break;
		case 2:
			selection = "_ID=";
			selectionArgs[0] = uri.getLastPathSegment();
			mCursor = mDB.query(DBHelper.MOVEMENTS_TABLE, null, selection, selectionArgs, null, null, null);			
			break;						
		default:
			throw new IllegalArgumentException ("Content URI pattern not recognizable: "+uri);
		}
		return mCursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
