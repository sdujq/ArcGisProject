package org.sdu.view.taskshow;

import java.util.List;

import org.sdu.dao.TaskDao;
import org.sdu.gis.R;
import org.sdu.pojo.Task;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TaskShowMhActivity extends Activity{

	ListView lv;
	public static TaskShowMhActivity currentActivity;
	public Handler h=new Handler(){
		@Override
		public void handleMessage(Message m){
			lv.setAdapter(new TaskShowAdapter());
		}
	};
	@Override
	public void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.taskshowmh);
		lv=(ListView) findViewById(R.id.listView1);
		currentActivity=this;
		lv.setAdapter(new TaskShowAdapter());
	}
	
	class TaskShowAdapter extends BaseAdapter{

		TaskDao taskDao=new TaskDao(TaskShowMhActivity.this);
		List<Task> data;
		public TaskShowAdapter(){
			data=taskDao.find();
		}
		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int arg0) {
			return data.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v=View.inflate(TaskShowMhActivity.this, R.layout.task_show_item, null);
			TextView tv1=(TextView)v.findViewById(R.id.cake_name);
			TextView tv2=(TextView)v.findViewById(R.id.cake_price);
			TextView tv3=(TextView)v.findViewById(R.id.cake_brand);
			TextView tv4=(TextView)v.findViewById(R.id.cake_timestamp);
			Task t=data.get(position);
			tv1.setText("任务代号:"+t.getId());
			tv2.setText("任务类型:"+t.getTaskType());
			tv3.setText("路段名称:"+t.getRoadName());
			tv4.setText("发布时间:"+t.getRealseTime());
			return v;
		}
		
	}
}
