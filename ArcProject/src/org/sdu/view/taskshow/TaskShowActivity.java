package org.sdu.view.taskshow;

import org.sdu.dao.TaskDao;
import org.sdu.db.DBHelper;
import org.sdu.dbaction.TaskAction;
import org.sdu.gis.R;
import org.sdu.pojo.Task;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TaskShowActivity extends Activity{

	
//	private int taskId;
	private Button ButtonTask1,ButtonTask2,ButtonTask3,ButtonTask4,ButtonMoreTask;
	private TextView tasknum11,tasknum21,tasknum31,tasknum41,
					tasktype12,tasktype22,tasktype32,tasktype42,
					roadname13,roadname23,roadname33,roadname43,
					makername14,makername24,makername34,makername44,
					makedate15,makedate25,makedate35,makedate45
					;
	private int num = 1;
	@Override
	public void onCreate(Bundle saved){
		super.onCreate(saved);
		setContentView(R.layout.task_list);
		
		
		TaskAction ta=new TaskAction(this);
		Task task=new Task();
		
		task.setRoadName("road 1");
		task.setTaskType("ee");
		task.setInspectionPersonId(001);
		task.setCreatePersonId(100);
		task.setRoadLineId(010);
		task.setStartTime(1100);
		task.setEndTime(1200);
		task.setRealseTime(1000);
		task.setContent("ww");
		task.setCycle(1);
		task.setTag("qq");
		task.setState("wancheng");
		
	//	ta.establishTask(task);
				
	//	Task str;
	//	str=ta.getDetail(1);
		ta.getDetail(4);
		task.getId();
		//String str1=str.getRoadName();
		System.out.println("--------"+ta.getDetail(1));
		System.out.println("********************"+ta.getTaskList());
		System.out.println("aaaaaaaaaaaaaa"+task.getId());
		
		
		
		 	ButtonMoreTask=(Button)findViewById(R.id.moretask);
	        ButtonTask1=(Button)findViewById(R.id.task1);
	        ButtonTask2=(Button)findViewById(R.id.task2);
	        ButtonTask3=(Button)findViewById(R.id.task3);
	        ButtonTask4=(Button)findViewById(R.id.task4);
		
		
	        tasknum11=(TextView)findViewById(R.id.tasknum11);
	        tasknum21=(TextView)findViewById(R.id.tasknum21);
	        tasknum31=(TextView)findViewById(R.id.tasknum31);
	        tasknum41=(TextView)findViewById(R.id.tasknum41);
	        
	        
	        tasktype12=(TextView)findViewById(R.id.tasktype12);
	        tasktype22=(TextView)findViewById(R.id.tasktype22);
	        tasktype32=(TextView)findViewById(R.id.tasktype32);
	        tasktype42=(TextView)findViewById(R.id.tasktype42);
	        
	        makername14=(TextView)findViewById(R.id.makername14);
	        makername24=(TextView)findViewById(R.id.makername24);
	        makername34=(TextView)findViewById(R.id.makername34);
	        makername44=(TextView)findViewById(R.id.makername44);
	        
	        makedate15=(TextView)findViewById(R.id.makedate15);
	        makedate25=(TextView)findViewById(R.id.makedate25);
	        makedate35=(TextView)findViewById(R.id.makedate35);
	        makedate45=(TextView)findViewById(R.id.makedate45);
	        
	        
	        Intent intent = getIntent();
	        
	        String show = intent.getStringExtra("one");
	        
	        if(show.equalsIgnoreCase("show")){
	        	
	        	
	        	
	        }
	        
	        
//	        DBHelper dbHelper = new DBHelper(TaskShowActivity.this);
	        
//	        
//	        class QueryListener implements OnClickListener{
//
//	    		@Override
//	    		public void onClick(View v) {
//	    			System.out.println("aaa------------------");
//	    			Log.d("myDebug", "myFirstDebugMsg");
//	    			
//	    			DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this,"test_mars_db");
//	    			SQLiteDatabase db = dbHelper.getReadableDatabase();
//	    			Cursor cursor = db.query("user", new String[]{"id","name"}, "id=?", new String[]{"1"}, null, null, null);
//	    			while(cursor.moveToNext()){
//	    				String name = cursor.getString(cursor.getColumnIndex("name"));
//	    				System.out.println("query--->" + name);
//	    			}
//	    		}

	        
	        
	        ButtonMoreTask.setText("����鿴��������");
	        
	        ButtonTask1.setText("����"+""+num);//�˴�num�����ڽ�������и��������ʱ���м�1�����������ʾ��
	        num++;
	        ButtonTask2.setText("����"+""+num);
	        num++;
	        ButtonTask3.setText("����"+""+num);
	        num++;
	        ButtonTask4.setText("����"+""+num);
	        num++;
	        
	        
	        
	        
	        
	    //    tasknum51=(TextView)findViewById(R.id.tasknum51);
	        
	        tasknum11.setText("������:"+"");//�˴��ո���Ϊ���������ݿ�õ������ź������ʾ��
	        tasknum21.setText("������:"+"");
	        tasknum31.setText("������:"+"");
	        tasknum41.setText("������:"+"");
	  //      tasknum51.setText(R.string.tasknum+"");

	    //    tasktype52=(TextView)findViewById(R.id.tasktype52);
	        
	        tasktype12.setText("��������:"+"");//����ո�����ͬ��
	        tasktype22.setText("��������:"+"");
	        tasktype32.setText("��������:"+"");
	        tasktype42.setText("��������:"+"");
	   //     tasktype52.setText(R.string.tasktype+"");
	        
	         roadname13=(TextView)findViewById(R.id.roadname13);
	        roadname23=(TextView)findViewById(R.id.roadname23);
	        roadname33=(TextView)findViewById(R.id.roadname33);
	        roadname43=(TextView)findViewById(R.id.roadname43);
	    //    roadname53=(TextView)findViewById(R.id.roadname53);
	        
	        roadname13.setText("·����:"+"");
	        roadname23.setText("·����:"+"");
	        roadname33.setText("·����:"+"");
	        roadname43.setText("·����:"+"");
	     //   roadname53.setText(R.string.roadname+"");
	        

	    //    makername54=(TextView)findViewById(R.id.makername54);
	        
	        makername14.setText("�ƶ���Ա��"+"");
	        makername24.setText("�ƶ���Ա��"+"");
	        makername34.setText("�ƶ���Ա��"+"");
	        makername44.setText("�ƶ���Ա��"+"");
	    //    makername54.setText(R.string.makername+"");
	        

	   //     makedate55=(TextView)findViewById(R.id.makedate55);
	        
	        makedate15.setText("�ƶ����ڣ�"+"");
	        makedate25.setText("�ƶ����ڣ�"+"");
	        makedate35.setText("�ƶ����ڣ�"+"");
	       makedate45.setText("�ƶ����ڣ�"+"");
	    //    makedate55.setText(R.string.date+"");
		
	       
	       

	       ButtonTask1.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
					
					//������һ��ʵ��ʱ�ĵط������������Ž����������ϸ��ʾ
					//��Ҫ�������ݵĴ���
					//����ֻ�ǽ��е����ť��ת����һ��activity
					
					Intent intent = new Intent();
					intent.setClass(TaskShowActivity.this, TaskDetialShow.class);
					//ʹ�����Intent����������
					TaskShowActivity.this.startActivity(intent);
						}
	       }
	        );
	       ButtonTask2.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
					
					//������һ��ʵ��ʱ�ĵط������������Ž����������ϸ��ʾ
					//��Ҫ�������ݵĴ���
					//����ֻ�ǽ��е����ť��ת����һ��activity
					
					Intent intent = new Intent();
					intent.setClass(TaskShowActivity.this, TaskDetialShow.class);
					//ʹ�����Intent����������
					TaskShowActivity.this.startActivity(intent);
						}
	       }
	        );
	       ButtonTask3.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
					
					//������һ��ʵ��ʱ�ĵط������������Ž����������ϸ��ʾ
					//��Ҫ�������ݵĴ���
					//����ֻ�ǽ��е����ť��ת����һ��activity
					
					Intent intent = new Intent();
					intent.setClass(TaskShowActivity.this, TaskDetialShow.class);
					//ʹ�����Intent����������
					TaskShowActivity.this.startActivity(intent);
						}
	       }
	        );
	       ButtonTask4.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
					
					//������һ��ʵ��ʱ�ĵط������������Ž����������ϸ��ʾ
					//��Ҫ�������ݵĴ���
					//����ֻ�ǽ��е����ť��ת����һ��activity
					
					Intent intent = new Intent();
					intent.setClass(TaskShowActivity.this, TaskDetialShow.class);
					//ʹ�����Intent����������
					TaskShowActivity.this.startActivity(intent);
						}
	       }
	        );
	       
	      
	}
	

	
	}

