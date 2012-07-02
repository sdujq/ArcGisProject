package org.sdu.view.taskshow;

import org.sdu.gis.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TaskDetialShow extends Activity{

	
	private TextView v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13;
	private Button b1,b2,b3;
	@Override
	public void onCreate(Bundle saved){
		super.onCreate(saved);
		setContentView(R.layout.details_task);
		
		v1=(TextView)findViewById(R.id.detial_task);
		
		v2=(TextView)findViewById(R.id.task_num);
		v3=(TextView)findViewById(R.id.task_type);
		v4=(TextView)findViewById(R.id.road_name);
		v5=(TextView)findViewById(R.id.task_neirong);
		v6=(TextView)findViewById(R.id.maker_name);
		v7=(TextView)findViewById(R.id.make_time);
		v8=(TextView)findViewById(R.id.worker_name);
		v9=(TextView)findViewById(R.id.cycle_time);
		v10=(TextView)findViewById(R.id.time_begin);
		v11=(TextView)findViewById(R.id.time_end);
		v12=(TextView)findViewById(R.id.over);
		v13=(TextView)findViewById(R.id.beizhu);
		
		v2.setText("任务编号:");
		v3.setText("任务类型:");
		v4.setText("路段名:");
		v5.setText("任务内容:");
		v6.setText("制定人员:");
		v7.setText("制定时间:");
		v8.setText("巡检人员:");
		v9.setText("巡检周期:");
		v10.setText("期限起:");
		v11.setText("期限止:");
		v12.setText("是否完成:");
		v13.setText("备注：");
		
		b1=(Button)findViewById(R.id.gettask);
		b1.setText("接受任务");
		b2=(Button)findViewById(R.id.lookmap);
		b2.setText("查看地图");
		b3=(Button)findViewById(R.id.completetask);
		b3.setText("完成任务");
		
	}
}