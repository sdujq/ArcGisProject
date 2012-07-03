package org.sdu.view.usermanager;

import org.sdu.gis.R;
import org.sdujq.frame.AbsShow;
import org.sdujq.frame.FrameActivity;
import org.sdujq.frame.FrameInfo;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;

public class AccountActivity extends FrameActivity{

	@Override
	public void onCreate(Bundle sintance){
		super.onCreate(sintance);
	}
	@Override
	public void loadInfo(){
		this.info=new FrameInfo();
		info.setTitle("用户管理");
		AbsShow[][] views=new AbsShow[1][];
		
		views[0]=new AbsShow[2];
		//LayoutParams params=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		views[0][0]=new AddUserView(this,R.layout.adduser,"添加用户");
		views[0][1]=new UserListView(this,R.layout.userlist,"用户列表");
		//views[2][1]=new AccountView(this,R.layout.white,"安全设置");
		
		info.setViews(views);
	}
}
