package org.sdu.view.taskshow;

import java.util.ArrayList;

import org.sdu.dbaction.TaskAction;
import org.sdu.gis.R;
import org.sdu.pojo.Task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TaskDetialShow extends Activity{

	
	private TextView v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13;
	private Button b1,b2,b3;
	int t_num;
	private String t_type,r_name,t_neirong,over,beizhu;
	long t_end;
	long t_begin;
	long c_time;
	int w_name;
	long m_time;
	int m_name;
	
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
		
		Intent intent = getIntent();
		String factorOneStr = intent.getStringExtra("one");
		int Button_id = Integer.parseInt(factorOneStr);
		
		
		TaskAction ta=new TaskAction(this);
    	Task task=new Task();
    	
    	
    	m_name=ta.getDetail(Button_id).getCreatePersonId();
    	t_num=ta.getDetail(Button_id).getId();
    	t_type=ta.getDetail(Button_id).getTaskType();
    	r_name=ta.getDetail(Button_id).getRoadName();
    	t_neirong=ta.getDetail(Button_id).getContent();
    	m_time=ta.getDetail(Button_id).getRealseTime();
    	w_name=ta.getDetail(Button_id).getInspectionPersonId();
    	c_time=ta.getDetail(Button_id).getCycle();
    	t_begin=ta.getDetail(Button_id).getStartTime();
    	t_end=ta.getDetail(Button_id).getEndTime();
    	over=ta.getDetail(Button_id).getState();
    	beizhu=ta.getDetail(Button_id).getTag();
    	
//    	
//        ArrayList<Task>ls=(ArrayList<Task>) ta.getTaskList();
//     
//        int alltask = ls.size();
//		
		
		v2.setText("������:"+t_num);
		v3.setText("��������:"+t_type);
		v4.setText("·����:"+r_name);
		v5.setText("��������:"+t_neirong);
		v6.setText("�ƶ���Ա:"+m_name);
		v7.setText("�ƶ�ʱ��:"+m_time);
		v8.setText("Ѳ����Ա:"+w_name);
		v9.setText("Ѳ������:"+c_time);
		v10.setText("������:"+t_begin);
		v11.setText("����ֹ:"+t_end);
		v12.setText("�Ƿ����:"+over);
		v13.setText("��ע��"+beizhu);
		
		
		b1=(Button)findViewById(R.id.gettask);
		b1.setText("��������");
		b2=(Button)findViewById(R.id.lookmap);
		b2.setText("�鿴��ͼ");
		b3=(Button)findViewById(R.id.completetask);
		b3.setText("�������");
		
	}
}