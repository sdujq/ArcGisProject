package org.sdu.view.taskshow;

import org.sdu.dao.RoadLineDao;
import org.sdu.dao.TaskDao;
import org.sdu.dao.UserDao;
import org.sdu.dbaction.Action;
import org.sdu.dbaction.TaskAction;
import org.sdu.gis.R;
import org.sdu.pojo.RoadLine;
import org.sdu.pojo.Task;
import org.sdu.view.taskinput.TaskInputActivity;
import org.sdujq.map.MapShowActivity;
import org.sdujq.map.TabHomeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TaskDetialShow extends Activity implements OnClickListener {

	public TextView v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13;
	private Button b1, b2, b3;
	int t_num;
	private String t_type, r_name, t_neirong, over, beizhu;
	long t_end;
	long t_begin;
	long c_time;
	String w_name;
	long m_time;
	String m_name;
	Task task;

	@Override
	public void onCreate(Bundle saved){
		super.onCreate(saved);
		setContentView(R.layout.details_task);
		getWindow().setLayout(500, 620);

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
		
		Intent intent = getIntent();
		String factorOneStr = intent.getStringExtra("one");
		int Button_id = Integer.parseInt(factorOneStr);
		
		UserDao ua=new UserDao(this);
		TaskAction ta=new TaskAction(this);
    	task=ta.getDetail(Button_id);
    	
    	
    	m_name=ua.get(task.getCreatePersonId())+"";
    	t_num=task.getId();
    	t_type=task.getTaskType();
    	r_name=task.getRoadName();
    	t_neirong=task.getContent();
    	m_time=task.getRealseTime();
    	w_name=ua.get(task.getInspectionPersonId())+"";
    	c_time=task.getCycle();
    	t_begin=task.getStartTime();
    	t_end=task.getEndTime();
    	over=task.getState();
    	beizhu=task.getTag();
    	if(over.equals("1")){
    		over="未接受";
    	}else if(over.equals("2")){
    		over="未完成";
    	}else if(over.equals("3")){
    		over="完成";			
    	}else if(over.equals("0")){
    		over="未发布";
    	}
//    	
//        ArrayList<Task>ls=(ArrayList<Task>) ta.getTaskList();
//     
//        int alltask = ls.size();
//		
		
		v2.setText("任务编号: "+t_num);
		v3.setText("任务类型: "+t_type);
		v4.setText("路段名称: "+r_name);
		v5.setText("任务描述: "+t_neirong);
		v6.setText("制定人员: "+m_name);
		v7.setText("制定时间: "+m_time);
		v8.setText("巡检人员: "+w_name);
		v9.setText("巡检周期: "+c_time);
		v10.setText("起始时间: "+t_begin);
		v11.setText("终止时间: "+t_end);
		v12.setText("是否完成: "+over);
		v13.setText("备注信息: "+beizhu);
		
		
		b1=(Button)findViewById(R.id.gettask);
		b1.setText("接受任务");
		b2=(Button)findViewById(R.id.lookmap);
		b2.setText("查看地图");
		b3=(Button)findViewById(R.id.completetask);
		b3.setText("完成任务");
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		Integer state=Integer.parseInt(task.getState().trim());
		if(state>=2||task.getInspectionPersonId()!=Action.currentUser.getId()){
			b1.setEnabled(false);
		}
		if(state==3||task.getInspectionPersonId()!=Action.currentUser.getId()){
			b3.setEnabled(false);
		}
		if(state==0){
			b1.setEnabled(false);
			b3.setEnabled(false);
			TaskInputActivity.currentTaskInputActivity.ReadTask(t_num);
			TabHomeActivity.home.mPager.setCurrentItem(0, true);
		}
	}

	@Override
	public void onClick(View v) {
		if (v == b1) {
			task.setState(2 + "");
			new TaskDao(TaskDetialShow.this).update(task);
		} else if (v == b2) {
			RoadLine road = (new RoadLineDao(TaskDetialShow.this)).get(task
					.getRoadLineId());
			MapShowActivity.startMapForShow(TaskDetialShow.this, road, false);
		} else if (v == b3) {
			task.setState(3 + "");
			new TaskDao(TaskDetialShow.this).update(task);
		}
		TaskShowMhActivity.currentActivity.refreshData();
		this.finish();
		overridePendingTransition(R.anim.scale_rotate,
				R.anim.my_alpha_action);
	}
}