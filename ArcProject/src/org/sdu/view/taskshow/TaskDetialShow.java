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
		
		v2.setText("������:");
		v3.setText("��������:");
		v4.setText("·����:");
		v5.setText("��������:");
		v6.setText("�ƶ���Ա:");
		v7.setText("�ƶ�ʱ��:");
		v8.setText("Ѳ����Ա:");
		v9.setText("Ѳ������:");
		v10.setText("������:");
		v11.setText("����ֹ:");
		v12.setText("�Ƿ����:");
		v13.setText("��ע��");
		
		
		
		b1=(Button)findViewById(R.id.gettask);
		b1.setText("��������");
		b2=(Button)findViewById(R.id.lookmap);
		b2.setText("�鿴��ͼ");
		b3=(Button)findViewById(R.id.completetask);
		b3.setText("�������");
		
	}
}