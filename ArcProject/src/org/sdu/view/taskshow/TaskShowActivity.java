package org.sdu.view.taskshow;

import org.sdu.gis.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class TaskShowActivity extends Activity{

	
	ListView tasklist;
	@Override
	public void onCreate(Bundle saved){
		super.onCreate(saved);
		setContentView(R.layout.task_list);
		
		
	}
}
