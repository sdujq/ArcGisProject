package org.sdu.view.taskshow;

import java.util.ArrayList;

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
	private Button ButtonTask1,ButtonTask2,ButtonTask3,ButtonTask4,ButtonMoreTask,ButtonUpPage;
	private TextView tasknum11,tasknum21,tasknum31,tasknum41,
					tasktype12,tasktype22,tasktype32,tasktype42,
					roadname13,roadname23,roadname33,roadname43,
					makername14,makername24,makername34,makername44,
					makedate15,makedate25,makedate35,makedate45
					;
	private int num ;//����num��
	private int d_num=0;//����������
	private int Button1_id,Button2_id,Button3_id,Button4_id;
	
	
	
	@Override
	public void onCreate(Bundle saved){
		super.onCreate(saved);
		setContentView(R.layout.task_list);
		
		
		
	
	//	ta.establishTask(task);
				

	
		
		
		
		 	ButtonMoreTask=(Button)findViewById(R.id.moretask);
		 	ButtonUpPage=(Button)findViewById(R.id.uppage);
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
	        
	        
	        roadname13=(TextView)findViewById(R.id.roadname13);
	        roadname23=(TextView)findViewById(R.id.roadname23);
	        roadname33=(TextView)findViewById(R.id.roadname33);
	        roadname43=(TextView)findViewById(R.id.roadname43);
	        
	        
	        
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
	        ButtonUpPage.setText("����鿴��һҳ");
	    
	 
	    	//	Task str;
	    	//	str=ta.getDetail(1);
	    	//	ta.getDetail(4);
	    	//	task.getId();
	    		//String str1=str.getRoadName();
	    	//	System.out.println("--------"+ta.getDetail(1));
	    		
	    		
	        TaskAction ta=new TaskAction(this);
	    	Task task=new Task();
	    	
	    	//ArrayList<Task>ls=(ArrayList<Task>) ta.getTaskList();
	        
	        ArrayList<Task>ls1=(ArrayList<Task>) ta.getTaskList();
	    		
	        int task_index=0;
	        int alltask = ls1.size();
	        
	        int t_num1,t_num2,t_num3,t_num4;
	   
	        String t_type1,t_type2,t_type3,t_type4;
	       
	        String r_name1,r_name2,r_name3,r_name4;
	       
	        int m_id1,m_id2,m_id3,m_id4;
	      
	        int m_date1,m_date2,m_date3,m_date4;
	        
	        if(alltask==0){
	        	 ButtonTask1.setText("����1"+""+(num+1));
	        	 tasknum11.setText("������:" );
	        	 tasktype12.setText("��������:");
	        	 roadname13.setText("·����:"+"");
	        	 makername14.setText("�ƶ���Ա��"+"" );
	        	 makedate15.setText("�ƶ����ڣ�"+"" );
	        	 
	        	 ButtonTask2.setText("����2"+""+(num+1));
	        	 tasknum21.setText("������:" );
	        	 tasktype22.setText("��������:");
	        	 roadname23.setText("·����:"+"");
	        	 makername24.setText("�ƶ���Ա��"+"" );
	        	 makedate25.setText("�ƶ����ڣ�"+"" );
	        	 
	        	 ButtonTask3.setText("����3"+""+(num+1));
	        	 tasknum31.setText("������:" );
	        	 tasktype32.setText("��������:");
	        	 roadname33.setText("·����:"+"");
	        	 makername34.setText("�ƶ���Ա��"+"" );
	        	 makedate35.setText("�ƶ����ڣ�"+"" );
	        	 
	        	 ButtonTask4.setText("����4"+""+(num+1));
	        	 tasknum41.setText("������:" );
	        	 tasktype42.setText("��������:");
	        	 roadname43.setText("·����:"+"");
	        	 makername44.setText("�ƶ���Ա��"+"" );
	        	 makedate45.setText("�ƶ����ڣ�"+"" );
	        	 Button1_id=0;
	        	 Button2_id=0;
	        	 Button3_id=0;
	        	 Button4_id=0;
	        }
	      
	       if(d_num<alltask)
	       {
	        num = d_num;
			  ButtonTask1.setText("����"+""+(num+1));
			  
			  task_index=d_num;
		        t_num1=ls1.get(task_index).getId();
		        tasknum11.setText("������:"+t_num1);//�˴��ո���Ϊ���������ݿ�õ������ź������ʾ��
		        t_type1=ls1.get(task_index).getTaskType();
		        tasktype12.setText("��������:"+""+t_type1);//����ո�����ͬ��
		        r_name1= ls1.get(task_index).getRoadName();
		        roadname13.setText("·����:"+""+r_name1);
		        m_id1= ls1.get(task_index).getInspectionPersonId();
		        makername14.setText("�ƶ���Ա��"+""+m_id1);
		        m_date1=(int) ls1.get(task_index).getRealseTime();
			    makedate15.setText("�ƶ����ڣ�"+""+m_date1);
			    d_num++;
			    Button1_id=t_num1;
			    
			    }
	       if(d_num<alltask)
	       {
			    	num = d_num;
			    	ButtonTask2.setText("����"+""+(num+1));
				  
				    task_index=d_num;
			        t_num2=ls1.get(task_index).getId();
			        tasknum21.setText("������:"+t_num2);//�˴��ո���Ϊ���������ݿ�õ������ź������ʾ��
			        t_type2=ls1.get(task_index).getTaskType();
			        tasktype22.setText("��������:"+""+t_type2);//����ո�����ͬ��
			        r_name2= ls1.get(task_index).getRoadName();
			        roadname23.setText("·����:"+""+r_name2);
			        m_id2= ls1.get(task_index).getInspectionPersonId();
			        makername24.setText("�ƶ���Ա��"+""+m_id2);
			        m_date2=(int) ls1.get(task_index).getRealseTime();
				    makedate25.setText("�ƶ����ڣ�"+""+m_date2);
				    d_num++;    
				    Button2_id=t_num2;
	       }
	       if(d_num<alltask)
	       {
				    num = d_num;
					  ButtonTask3.setText("����"+""+(num+1));
					  
					   task_index=d_num;
				        t_num3=ls1.get(task_index).getId();
				        tasknum31.setText("������:"+t_num3);//�˴��ո���Ϊ���������ݿ�õ������ź������ʾ��
				        t_type3=ls1.get(task_index).getTaskType();
				        tasktype32.setText("��������:"+""+t_type3);//����ո�����ͬ��
				        r_name3= ls1.get(task_index).getRoadName();
				        roadname33.setText("·����:"+""+r_name3);
				        m_id3= ls1.get(task_index).getInspectionPersonId();
				        makername34.setText("�ƶ���Ա��"+""+m_id3);
				        m_date3=(int) ls1.get(task_index).getRealseTime();
					    makedate35.setText("�ƶ����ڣ�"+""+m_date3);
					    d_num++;
					    Button3_id=t_num3;
	       }
					   if(d_num<alltask){
						   
					   
						num = d_num;
						  ButtonTask4.setText("����"+""+(num+1));
						  
						    task_index=d_num;
					        t_num4=ls1.get(task_index).getId();
					        tasknum41.setText("������:"+t_num4);//�˴��ո���Ϊ���������ݿ�õ������ź������ʾ��
					        t_type4=ls1.get(task_index).getTaskType();
					        tasktype42.setText("��������:"+""+t_type4);//����ո�����ͬ��
					        r_name4= ls1.get(task_index).getRoadName();
					        roadname43.setText("·����:"+""+r_name4);
					        m_id4= ls1.get(task_index).getInspectionPersonId();
					        makername44.setText("�ƶ���Ա��"+""+m_id4);
					        m_date4=(int) ls1.get(task_index).getRealseTime();
						    makedate45.setText("�ƶ����ڣ�"+""+m_date4);
						    d_num++;
						    Button4_id=t_num4;
					   }

	       ButtonTask1.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
	    			
	    		   if(Button1_id==0)
						Toast.makeText(getApplicationContext(), "û������",
			        		     Toast.LENGTH_SHORT).show();
	    		   else
	    		   {
					Intent intent = new Intent();
					String str=""+Button1_id;
					intent.putExtra("one",str);
					
					intent.setClass(TaskShowActivity.this, TaskDetialShow.class);
					//ʹ�����Intent����������
					TaskShowActivity.this.startActivity(intent);
						}
	    	   }
	       }
	        );
	       ButtonTask2.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
					
	    		   if(Button2_id==0)
						Toast.makeText(getApplicationContext(), "û������",
			        		     Toast.LENGTH_SHORT).show();
	    		   else{
					Intent intent = new Intent();
					String str=""+Button2_id;
					intent.putExtra("one",str);
					intent.setClass(TaskShowActivity.this, TaskDetialShow.class);
					//ʹ�����Intent����������
					TaskShowActivity.this.startActivity(intent);
						}
	    	   }
	       }
	        );
	       ButtonTask3.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
					if(Button3_id==0)
						Toast.makeText(getApplicationContext(), "û������",
			        		     Toast.LENGTH_SHORT).show();
					else
					{
					Intent intent = new Intent();
					String str=""+Button3_id;
					intent.putExtra("one",str);
					intent.setClass(TaskShowActivity.this, TaskDetialShow.class);
					//ʹ�����Intent����������
					TaskShowActivity.this.startActivity(intent);
						}
	    	   }
	       }
	        );
	       ButtonTask4.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
	    		  
	    		   if(Button3_id==0)
						Toast.makeText(getApplicationContext(), "û������",
			        		     Toast.LENGTH_SHORT).show();
	    		   else
	    		   {
	    			   
					Intent intent = new Intent();
					String str=""+Button4_id;
					intent.putExtra("one",str);
					intent.setClass(TaskShowActivity.this, TaskDetialShow.class);
					//ʹ�����Intent����������
					TaskShowActivity.this.startActivity(intent);
						}
	    	   }
	       }
	        );
	       ButtonMoreTask.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
					
					moreTask();
						}
	       }
	        );
	       
	       ButtonUpPage.setOnClickListener( new Button.OnClickListener()
	       {
	    	   public void onClick(View v){
					
					upPage();
						}
	       }
	        );
	}
	
	public void moreTask(){
		
		 int t_num1,t_num2,t_num3,t_num4;
		 String t_type1,t_type2,t_type3,t_type4;
		 String r_name1,r_name2,r_name3,r_name4;
		  int m_id1,m_id2,m_id3,m_id4;
		  int m_date1,m_date2,m_date3,m_date4;
		 
        TaskAction ta=new TaskAction(this);
	    	Task task=new Task();
	    	
	    	//ArrayList<Task>ls=(ArrayList<Task>) ta.getTaskList();
	        
	        ArrayList<Task>ls=(ArrayList<Task>) ta.getTaskList();
	     
	        int alltask = ls.size();
	        if(d_num>=alltask){
	        	
	        	
	        	Toast.makeText(getApplicationContext(), "�Ѿ������һҳ",
	        		     Toast.LENGTH_SHORT).show();
	        }
	        		
	        else{	
	        
	        
		if(d_num<alltask){
			num = d_num;
			  ButtonTask1.setText("����"+""+(num+1));
			  
			   int task_index=d_num;
		        t_num1=ls.get(task_index).getId();
		        tasknum11.setText("������:"+t_num1);//�˴��ո���Ϊ���������ݿ�õ������ź������ʾ��
		        t_type1=ls.get(task_index).getTaskType();
		        tasktype12.setText("��������:"+""+t_type1);//����ո�����ͬ��
		        r_name1= ls.get(task_index).getRoadName();
		        roadname13.setText("·����:"+""+r_name1);
		        m_id1= ls.get(task_index).getInspectionPersonId();
		        makername14.setText("�ƶ���Ա��"+""+m_id1);
		        m_date1=(int) ls.get(task_index).getRealseTime();
			    makedate15.setText("�ƶ����ڣ�"+""+m_date1);
		       d_num++;
		       Button1_id=t_num1;
		}
		else{
			
				ButtonTask1.setText("����0"+"");
				tasknum11.setText("������:");
				tasktype12.setText("��������:");
				roadname13.setText("·����:");
				makername14.setText("�ƶ���Ա��");
				makedate15.setText("�ƶ����ڣ�");
				Button1_id=0;
			
		}
		
			if(d_num<alltask){
			
				num = d_num;
				  ButtonTask2.setText("����"+""+(num+1));
				  
				   int task_index=d_num;
			        t_num2=ls.get(task_index).getId();
			        tasknum21.setText("������:"+t_num2);//�˴��ո���Ϊ���������ݿ�õ������ź������ʾ��
			        t_type2=ls.get(task_index).getTaskType();
			        tasktype22.setText("��������:"+""+t_type2);//����ո�����ͬ��
			        r_name2= ls.get(task_index).getRoadName();
			        roadname23.setText("·����:"+""+r_name2);
			        m_id2= ls.get(task_index).getInspectionPersonId();
			        makername24.setText("�ƶ���Ա��"+""+m_id2);
			        m_date2=(int) ls.get(task_index).getRealseTime();
				    makedate25.setText("�ƶ����ڣ�"+""+m_date2);
			       d_num++;
			       Button2_id=t_num2;
			}
			else{
				
				ButtonTask2.setText("����0"+"");
				tasknum21.setText("������:");
				tasktype22.setText("��������:");
				roadname23.setText("·����:");
				makername24.setText("�ƶ���Ա��");
				makedate25.setText("�ƶ����ڣ�");
				Button2_id=0;
			
		}
			
			
				if(d_num<alltask){
					
					num = d_num;
					  ButtonTask3.setText("����"+""+(num+1));
					  
					   int task_index=d_num;
				        t_num3=ls.get(task_index).getId();
				        tasknum31.setText("������:"+t_num3);//�˴��ո���Ϊ���������ݿ�õ������ź������ʾ��
				        t_type3=ls.get(task_index).getTaskType();
				        tasktype32.setText("��������:"+""+t_type3);//����ո�����ͬ��
				        r_name3= ls.get(task_index).getRoadName();
				        roadname33.setText("·����:"+""+r_name3);
				        m_id3= ls.get(task_index).getInspectionPersonId();
				        makername34.setText("�ƶ���Ա��"+""+m_id3);
				        m_date3=(int) ls.get(task_index).getRealseTime();
					    makedate35.setText("�ƶ����ڣ�"+""+m_date3);
				       d_num++;
				       Button3_id=t_num3;
				}
				else{
					
					ButtonTask3.setText("����0"+"");
					tasknum31.setText("������:");
					tasktype32.setText("��������:");
					roadname33.setText("·����:");
					makername34.setText("�ƶ���Ա��");
					makedate35.setText("�ƶ����ڣ�");
				 Button3_id=0;
				
			}
				
				
					if(d_num<alltask){
						
							num = d_num;
						  ButtonTask4.setText("����"+""+(num+1));
						  
						   int task_index=d_num;
					        t_num4=ls.get(task_index).getId();
					        tasknum41.setText("������:"+t_num4);//�˴��ո���Ϊ���������ݿ�õ������ź������ʾ��
					        t_type4=ls.get(task_index).getTaskType();
					        tasktype42.setText("��������:"+""+t_type4);//����ո�����ͬ��
					        r_name4= ls.get(task_index).getRoadName();
					        roadname43.setText("·����:"+""+r_name4);
					        m_id4= ls.get(task_index).getInspectionPersonId();
					        makername44.setText("�ƶ���Ա��"+""+m_id4);
					        m_date4=(int) ls.get(task_index).getRealseTime();
						    makedate45.setText("�ƶ����ڣ�"+""+m_date4);
					       d_num++;
					       Button4_id=t_num4;
					}
					else{
						
						ButtonTask4.setText("����0"+"");
						tasknum41.setText("������:");
						tasktype42.setText("��������:");
						roadname43.setText("·����:");
						makername44.setText("�ƶ���Ա��");
						makedate45.setText("�ƶ����ڣ�");
						Button4_id=0;
					
				}
	        }
	}
	public void upPage(){
		
		
		 int t_num1,t_num2,t_num3,t_num4;
		 String t_type1,t_type2,t_type3,t_type4;
		 String r_name1,r_name2,r_name3,r_name4;
		  int m_id1,m_id2,m_id3,m_id4;
		  int m_date1,m_date2,m_date3,m_date4;
		 
        TaskAction ta=new TaskAction(this);
	    
	        ArrayList<Task>ls=(ArrayList<Task>) ta.getTaskList();
	        
	      int page = d_num/4;
	      d_num = page*4;
	        if(page==0){
	        	
	        	
	        	Toast.makeText(getApplicationContext(), "�Ѿ��ǵ�һҳ",
	        		     Toast.LENGTH_SHORT).show();
	        }
	        
	        
	        
	    	if(d_num>0){
				
				num = d_num;
			  ButtonTask4.setText("����"+""+num);
			  
			   int task_index=d_num-1;
		        t_num4=ls.get(task_index).getId();
		        tasknum41.setText("������:"+t_num4);//�˴��ո���Ϊ���������ݿ�õ������ź������ʾ��
		        t_type4=ls.get(task_index).getTaskType();
		        tasktype42.setText("��������:"+""+t_type4);//����ո�����ͬ��
		        r_name4= ls.get(task_index).getRoadName();
		        roadname43.setText("·����:"+""+r_name4);
		        m_id4= ls.get(task_index).getInspectionPersonId();
		        makername44.setText("�ƶ���Ա��"+""+m_id4);
		        m_date4=(int) ls.get(task_index).getRealseTime();
			    makedate45.setText("�ƶ����ڣ�"+""+m_date4);
			    d_num--;
		       Button4_id=t_num4;
		}
	    	
	    	if(d_num>0){
				
				num = d_num;
				  ButtonTask3.setText("����"+""+num);
				  
				   int task_index=d_num-1;
			        t_num3=ls.get(task_index).getId();
			        tasknum31.setText("������:"+t_num3);//�˴��ո���Ϊ���������ݿ�õ������ź������ʾ��
			        t_type3=ls.get(task_index).getTaskType();
			        tasktype32.setText("��������:"+""+t_type3);//����ո�����ͬ��
			        r_name3= ls.get(task_index).getRoadName();
			        roadname33.setText("·����:"+""+r_name3);
			        m_id3= ls.get(task_index).getInspectionPersonId();
			        makername34.setText("�ƶ���Ա��"+""+m_id3);
			        m_date3=(int) ls.get(task_index).getRealseTime();
				    makedate35.setText("�ƶ����ڣ�"+""+m_date3);
				    d_num--;
			       Button3_id=t_num3;
			}
	    	
	    	
	    	if(d_num>0){
				
				num = d_num;
				  ButtonTask2.setText("����"+""+num);
				  
				   int task_index=d_num-1;
			        t_num2=ls.get(task_index).getId();
			        tasknum21.setText("������:"+t_num2);//�˴��ո���Ϊ���������ݿ�õ������ź������ʾ��
			        t_type2=ls.get(task_index).getTaskType();
			        tasktype22.setText("��������:"+""+t_type2);//����ո�����ͬ��
			        r_name2= ls.get(task_index).getRoadName();
			        roadname23.setText("·����:"+""+r_name2);
			        m_id2= ls.get(task_index).getInspectionPersonId();
			        makername24.setText("�ƶ���Ա��"+""+m_id2);
			        m_date2=(int) ls.get(task_index).getRealseTime();
				    makedate25.setText("�ƶ����ڣ�"+""+m_date2);
			       d_num--;
			       Button2_id=t_num2;
			}
	    	
			if(d_num>0){
				num = d_num;
				  ButtonTask1.setText("����"+""+num);
				  
				   int task_index=d_num-1;
			        t_num1=ls.get(task_index).getId();
			        tasknum11.setText("������:"+t_num1);//�˴��ո���Ϊ���������ݿ�õ������ź������ʾ��
			        t_type1=ls.get(task_index).getTaskType();
			        tasktype12.setText("��������:"+""+t_type1);//����ո�����ͬ��
			        r_name1= ls.get(task_index).getRoadName();
			        roadname13.setText("·����:"+""+r_name1);
			        m_id1= ls.get(task_index).getInspectionPersonId();
			        makername14.setText("�ƶ���Ա��"+""+m_id1);
			        m_date1=(int) ls.get(task_index).getRealseTime();
				    makedate15.setText("�ƶ����ڣ�"+""+m_date1);
			       d_num--;
			       Button1_id=t_num1;
			}
		
				
				
				
				
					
					
					
	}
	
}
	


