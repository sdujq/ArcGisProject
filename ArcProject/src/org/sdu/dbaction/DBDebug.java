package org.sdu.dbaction;

import java.util.ArrayList;

import org.sdu.gis.R;
import org.sdu.pojo.Bug;
import org.sdu.pojo.Task;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
 
public class DBDebug extends Activity{
	private TaskAction ta;
	private BugAction ba;
	@Override
	public void onCreate(Bundle s){
		super.onCreate(s);
		setContentView(R.layout.white);
		ta=new TaskAction(this);
		ba=new BugAction(this);
		//添加任务
		Task task=new Task();
		task.setRoadName("舜华路");
		task.setCreatePersonId(1);
		task.setContent("巡检");
		task.setStartTime(10);
		task.setState("0");
		ta.establishTask(task);
		
		//获取任务的相关信息
		Task t=ta.getDetail(1);
		Log.i("TASK", t.toString());
		//修改任务
		task.setState("1");
		ta.releaseTask(task);
		//获取任务的相关信息
		Task t1=ta.getDetail(1);
		Log.i("TASK", t1.toString());
		//获取任务列表
		ArrayList<Task>list=(ArrayList<Task>)ta.getTaskList();
		Log.i("TASK",list.size()+"");
		
		Bug bug=new Bug();
		bug.setContent("管道漏水");
		bug.setState("0");
		bug.setPoint("12,23");
		bug.setBugTypeId(12);
		//save
		ba.establishBug(bug);
		//get
		Bug b=ba.getDetail(1);
		Log.i("BUG",b.getId()+" "+b.getContent()+" "+b.getState()+" "+b.getPoint()+" "+b.getBugTypeId());
		//update
		bug.setState("1");
		ba.finish(1);
		Bug b1=ba.getDetail(1);
		Log.i("BUG",b1.getId()+" "+b1.getContent()+" "+b1.getState()+" "+b1.getPoint()+" "+b1.getBugTypeId());
	}
}
