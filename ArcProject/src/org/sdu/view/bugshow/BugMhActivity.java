package org.sdu.view.bugshow;

import org.sdu.gis.R;
import org.sdujq.frame.AbsShow;
import org.sdujq.frame.FrameActivity;
import org.sdujq.frame.FrameInfo;

import android.os.Bundle;

public class BugMhActivity extends FrameActivity{
	
	@Override
	public void onCreate(Bundle sintance){
		super.onCreate(sintance);
	}
	@Override
	public void loadInfo(){
		this.info=new FrameInfo();
		info.setTitle("�����ѯ");
		AbsShow[][] views=new AbsShow[2][];
		
		views[0]=new AbsShow[1];
		views[0][0]=new BugSearchView(this,R.layout.bug_search,"��ѯ����");
		views[1]=new AbsShow[1];
		views[1][0]=new BugListView(this,R.layout.bug_listview,"�����б�");
		
		info.setViews(views);
	}
}
