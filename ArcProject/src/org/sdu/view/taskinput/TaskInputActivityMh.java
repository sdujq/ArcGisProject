package org.sdu.view.taskinput;

import org.sdu.gis.R;
import org.sdu.view.usermanager.AddUserView;
import org.sdujq.frame.AbsShow;
import org.sdujq.frame.FrameActivity;
import org.sdujq.frame.FrameInfo;

import android.os.Bundle;

public class TaskInputActivityMh extends FrameActivity{
	@Override
	public void onCreate(Bundle sintance){
		super.onCreate(sintance);
	}
	@Override
	public void loadInfo() {
		this.info=new FrameInfo();
		info.setTitle("任务录入");
		AbsShow[][] views=new AbsShow[1][];
		
		views[0]=new AbsShow[1];
		views[0][1]=new AddUserView(this,R.layout.task_input,"任务录入");
		//views[0][1]=new UserListView(this,R.layout.userlist,"用户列表");
		
		info.setViews(views);
	}

}
