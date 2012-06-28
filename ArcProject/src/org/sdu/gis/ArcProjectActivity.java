package org.sdu.gis;

import org.sdu.db.DBHelper;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
public class ArcProjectActivity extends Activity {
	public static DBHelper dbhelper;
	public SQLiteDatabase db;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	dbhelper = new DBHelper(this);
		db = dbhelper.getWritableDatabase();
    }
}