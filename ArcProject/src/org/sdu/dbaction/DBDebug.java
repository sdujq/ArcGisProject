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
		//�������
		Task task=new Task();
		task.setRoadName("˴��·");
		task.setCreatePersonId(1);
		task.setContent("Ѳ��");
		task.setStartTime(10);
		task.setState("0");
		ta.establishTask(task);
		
		//��ȡ����������Ϣ
		Task t=ta.getDetail(1);
		Log.i("TASK", t.toString());
		//�޸�����
		task.setState("1");
		ta.releaseTask(task);
		//��ȡ����������Ϣ
		Task t1=ta.getDetail(1);
		Log.i("TASK", t1.toString());
		//��ȡ�����б�
		ArrayList<Task>list=(ArrayList<Task>)ta.getTaskList();
		Log.i("TASK",list.size()+"");
		
		Bug bug=new Bug();
		bug.setContent("�ܵ�©ˮ");
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
