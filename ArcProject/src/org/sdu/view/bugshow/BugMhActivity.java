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
		info.setTitle("问题查询");
		AbsShow[][] views=new AbsShow[2][];
		
		views[0]=new AbsShow[1];
		views[0][0]=new BugSearchView(this,R.layout.bug_search,"查询条件");
		views[1]=new AbsShow[1];
		views[1][0]=new BugListView(this,R.layout.bug_listview,"问题列表");
		
		info.setViews(views);
	}
}
