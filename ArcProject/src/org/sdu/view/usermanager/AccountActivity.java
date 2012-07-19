package org.sdu.view.usermanager;

import org.sdu.gis.R;
import org.sdujq.frame.AbsShow;
import org.sdujq.frame.FrameActivity;
import org.sdujq.frame.FrameInfo;

import android.os.Bundle;

public class AccountActivity extends FrameActivity{

	@Override
	public void onCreate(Bundle sintance){
		super.onCreate(sintance);
	}
	@Override
	public void loadInfo(){
		this.info=new FrameInfo();
		info.setTitle("用户管理");
		AbsShow[][] views=new AbsShow[2][];
		
		views[0]=new AbsShow[2];
		//LayoutParams params=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		views[0][0]=new AddUserView(this,R.layout.adduser,"添加用户");
		views[0][1]=new UserListView(this,R.layout.userlist,"用户列表");
		views[1]=new AbsShow[1];
		views[1][0]=new AddUserView(this,R.layout.adduser,"系统参数");
		//views[2][1]=new AccountView(this,R.layout.white,"安全设置");
		
		info.setViews(views);
	}
}
