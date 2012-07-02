package org.sdu.gis;

import org.sdu.db.DBHelper;
import org.sdu.dbaction.DBDebug;
import org.sdu.view.buginput.BugInputActivity;
import org.sdu.view.bugshow.BugShowActivity;
import org.sdu.view.taskinput.TaskInputActivity;
import org.sdu.view.taskshow.TaskShowActivity;


import org.sdujq.map.MapShowActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
public class ArcProjectActivity extends Activity {
	public static DBHelper dbhelper;
	public SQLiteDatabase db;
	ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debuglist);
    	dbhelper = new DBHelper(this);
		db = dbhelper.getWritableDatabase();
		lv=(ListView) findViewById(R.id.listView1);
		lv.setAdapter(new DebugAdapter());
    }
    
    class DebugAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 7;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int n, View arg1, ViewGroup arg2) {
			View v=View.inflate(ArcProjectActivity.this, R.layout.lst_item, null);
			TextView t=(TextView) v.findViewById(R.id.cake_name);
			if(n==0){
				t.setText("¼������������");
				v.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View v) {
						Intent i=new Intent();
						i.setClass(ArcProjectActivity.this, TaskInputActivity.class);
						ArcProjectActivity.this.startActivity(i);
					}
				});
			}else if(n==1){
				t.setText("�����ѯ�������");
				v.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View v) {
						Intent i=new Intent();
						i.setClass(ArcProjectActivity.this, TaskShowActivity.class);
						ArcProjectActivity.this.startActivity(i);
					}
				});
			}else if(n==2){
				t.setText("¼������������");
				v.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View v) {
						Intent i=new Intent();
						i.setClass(ArcProjectActivity.this, BugInputActivity.class);
						ArcProjectActivity.this.startActivity(i);
					}
				});
			}else if(n==3){
				t.setText("�����ѯ�������");
				v.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View v) {
						Intent i=new Intent();
						i.setClass(ArcProjectActivity.this, BugShowActivity.class);
						ArcProjectActivity.this.startActivity(i);
					}
				});
			}else if(n==4){
				t.setText("�������");
				v.setOnClickListener(new OnClickListener() {	
					
					@Override
					public void onClick(View v) {
						Intent i=new Intent();
						i.setClass(ArcProjectActivity.this, DBDebug.class);
						ArcProjectActivity.this.startActivity(i);
					}
				});
			}else if(n==5){
				t.setText("��ͼ�������");
				v.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View v) {
						Intent i=new Intent();
						i.setClass(ArcProjectActivity.this, MapShowActivity.class);
						ArcProjectActivity.this.startActivity(i);
					}
				});
			}
			return v;
		}
    	
    }
}