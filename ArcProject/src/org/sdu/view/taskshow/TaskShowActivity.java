package org.sdu.view.taskshow;

import org.sdu.gis.R;

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
		
		 ButtonMoreTask=(Button)findViewById(R.id.moretask);
	        ButtonTask1=(Button)findViewById(R.id.task1);
	        ButtonTask2=(Button)findViewById(R.id.task2);
	        ButtonTask3=(Button)findViewById(R.id.task3);
	        ButtonTask4=(Button)findViewById(R.id.task4);
		
		
	        ButtonMoreTask.setText("点击查看更多任务");
	        
	        ButtonTask1.setText("任务"+""+num);//此处num、是在将来如果有更多的任务时进行加1操作后进行显示的
	        num++;
	        ButtonTask2.setText("任务"+""+num);
	        num++;
	        ButtonTask3.setText("任务"+""+num);
	        num++;
	        ButtonTask4.setText("任务"+""+num);
	        num++;
	        
	        
	        
	        
	        
	        tasknum11=(TextView)findViewById(R.id.tasknum11);
	        tasknum21=(TextView)findViewById(R.id.tasknum21);
	        tasknum31=(TextView)findViewById(R.id.tasknum31);
	        tasknum41=(TextView)findViewById(R.id.tasknum41);
	    //    tasknum51=(TextView)findViewById(R.id.tasknum51);
	        
	        tasknum11.setText("任务编号:"+"");//此处空格是为将来从数据库得到任务编号后进行显示的
	        tasknum21.setText("任务编号:"+"");
	        tasknum31.setText("任务编号:"+"");
	        tasknum41.setText("任务编号:"+"");
	  //      tasknum51.setText(R.string.tasknum+"");
	        
	        tasktype12=(TextView)findViewById(R.id.tasktype12);
	        tasktype22=(TextView)findViewById(R.id.tasktype22);
	        tasktype32=(TextView)findViewById(R.id.tasktype32);
	        tasktype42=(TextView)findViewById(R.id.tasktype42);
	    //    tasktype52=(TextView)findViewById(R.id.tasktype52);
	        
	        tasktype12.setText("任务类型:"+"");//此书空格作用同上
	        tasktype22.setText("任务类型:"+"");
	        tasktype32.setText("任务类型:"+"");
	        tasktype42.setText("任务类型:"+"");
	   //     tasktype52.setText(R.string.tasktype+"");
	        
	         roadname13=(TextView)findViewById(R.id.roadname13);
	        roadname23=(TextView)findViewById(R.id.roadname23);
	        roadname33=(TextView)findViewById(R.id.roadname33);
	        roadname43=(TextView)findViewById(R.id.roadname43);
	    //    roadname53=(TextView)findViewById(R.id.roadname53);
	        
	        roadname13.setText("路段名:"+"");
	        roadname23.setText("路段名:"+"");
	        roadname33.setText("路段名:"+"");
	        roadname43.setText("路段名:"+"");
	     //   roadname53.setText(R.string.roadname+"");
	        
	        makername14=(TextView)findViewById(R.id.makername14);
	        makername24=(TextView)findViewById(R.id.makername24);
	        makername34=(TextView)findViewById(R.id.makername34);
	        makername44=(TextView)findViewById(R.id.makername44);
	    //    makername54=(TextView)findViewById(R.id.makername54);
	        
	        makername14.setText("制定人员："+"");
	        makername24.setText("制定人员："+"");
	        makername34.setText("制定人员："+"");
	        makername44.setText("制定人员："+"");
	    //    makername54.setText(R.string.makername+"");
	        
	        makedate15=(TextView)findViewById(R.id.makedate15);
	        makedate25=(TextView)findViewById(R.id.makedate25);
	        makedate35=(TextView)findViewById(R.id.makedate35);
	        makedate45=(TextView)findViewById(R.id.makedate45);
	   //     makedate55=(TextView)findViewById(R.id.makedate55);
	        
	        makedate15.setText("制定日期："+"");
	        makedate25.setText("制定日期："+"");
	        makedate35.setText("制定日期："+"");
	       makedate45.setText("制定日期："+"");
	    //    makedate55.setText(R.string.date+"");
		
	       
	       

	       ButtonTask1.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
					
					//留作下一个实现时的地方，根据任务编号进行任务的详细显示
					//需要进行数据的传输
					//现在只是进行点击按钮跳转到另一个activity
					
					Intent intent = new Intent();
					intent.setClass(TaskShowActivity.this, TaskDetialShow.class);
					//使用这个Intent对象来启动
					TaskShowActivity.this.startActivity(intent);
						}
	       }
	        );
	       ButtonTask2.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
					
					//留作下一个实现时的地方，根据任务编号进行任务的详细显示
					//需要进行数据的传输
					//现在只是进行点击按钮跳转到另一个activity
					
					Intent intent = new Intent();
					intent.setClass(TaskShowActivity.this, TaskDetialShow.class);
					//使用这个Intent对象来启动
					TaskShowActivity.this.startActivity(intent);
						}
	       }
	        );
	       ButtonTask3.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
					
					//留作下一个实现时的地方，根据任务编号进行任务的详细显示
					//需要进行数据的传输
					//现在只是进行点击按钮跳转到另一个activity
					
					Intent intent = new Intent();
					intent.setClass(TaskShowActivity.this, TaskDetialShow.class);
					//使用这个Intent对象来启动
					TaskShowActivity.this.startActivity(intent);
						}
	       }
	        );
	       ButtonTask4.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
					
					//留作下一个实现时的地方，根据任务编号进行任务的详细显示
					//需要进行数据的传输
					//现在只是进行点击按钮跳转到另一个activity
					
					Intent intent = new Intent();
					intent.setClass(TaskShowActivity.this, TaskDetialShow.class);
					//使用这个Intent对象来启动
					TaskShowActivity.this.startActivity(intent);
						}
	       }
	        );
	       
	      
	}
	

	
		
		
	}

