package org.sdu.gis;

import org.sdu.dao.BugTypeDao;
import org.sdu.dao.RoadLineDao;
import org.sdu.db.DBHelper;
import org.sdu.pojo.BugType;
import org.sdu.pojo.RoadLine;
import org.sdu.view.buginput.BugInputActivity;
import org.sdu.view.bugshow.BugShowActivity;
import org.sdu.view.taskinput.TaskInputActivity;
import org.sdu.view.taskshow.TaskShowActivity;
import org.sdu.view.usermanager.LoginActivity;
import org.sdujq.map.MapShowActivity;
import org.sdujq.map.TabHomeActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
				t.setText("录入任务调试入口");
				v.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View v) {
						Intent i=new Intent();
						i.setClass(ArcProjectActivity.this, TaskInputActivity.class);
						ArcProjectActivity.this.startActivity(i);
					}
				});
			}else if(n==1){
				t.setText("任务查询调试入口");
				v.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View v) {
						String show = "show";
						Intent i=new Intent();
						i.putExtra("one",show);
						i.setClass(ArcProjectActivity.this, TaskShowActivity.class);
						ArcProjectActivity.this.startActivity(i);
						
						
						
					}
				});
			}else if(n==2){
				t.setText("录入问题调试入口");
				v.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View v) {
						Intent i=new Intent();
						i.setClass(ArcProjectActivity.this, BugInputActivity.class);
						ArcProjectActivity.this.startActivity(i);
					}
				});
			}else if(n==3){
				t.setText("问题查询调试入口");
				v.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View v) {
						Intent i=new Intent();
						i.setClass(ArcProjectActivity.this, BugShowActivity.class);
						ArcProjectActivity.this.startActivity(i);
					}
				});
			}else if(n==4){
				t.setText("人员管理");
				v.setOnClickListener(new OnClickListener() {	
					
					@Override
					public void onClick(View v) {
						Intent i=new Intent();
						i.setClass(ArcProjectActivity.this, LoginActivity.class);
						ArcProjectActivity.this.startActivity(i);
					}
				});
			}else if(n==5){
				t.setText("地图调试入口");
				v.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View v) {
						RoadLine r=(new RoadLineDao(ArcProjectActivity.this)).get(4);
						MapShowActivity.startMapForShow(ArcProjectActivity.this, null, false);
					}
				});
			}else if(n==6){
				t.setText("主界面调试入口");
				v.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View v) {
						BugTypeDao dao=new BugTypeDao(ArcProjectActivity.this);
						BugType bt=new BugType();
						bt.setName("丢了井盖");
						dao.insert(bt);
						bt.setName("管道破裂");
						dao.insert(bt);
						Intent it =new Intent();
						it.setClass(ArcProjectActivity.this, TabHomeActivity.class);
						ArcProjectActivity.this.startActivity(it);
					}
				});
			}
			
			return v;
		}
    	
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK){
			int id=data.getIntExtra("id", -1);
			Log.e("qq", "saved road id is "+id);
		}
	}}