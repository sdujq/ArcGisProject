package org.sdu.view.taskinput;

import org.sdujq.frame.AbsShow;
import org.sdujq.frame.FrameActivity;

import android.view.View;

public class TaskInputView extends AbsShow{

	public TaskInputView(FrameActivity activity, int layout, String name) {
		super(activity, layout, name);
	}

	@Override
	public View initView() {
		return view;
	}

}
