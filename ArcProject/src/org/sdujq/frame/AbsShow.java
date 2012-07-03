package org.sdujq.frame;

import org.sdu.gis.R;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;

public abstract class AbsShow {
	public static AbsShow currentView;
	public FrameActivity activity;
	public View view;
	public String name;
	public LayoutParams params;
	public AbsShow faView;

	public AbsShow(FrameActivity activity, int layout, String name) {
		this(activity, layout, null, name, null);
	}

	public AbsShow(FrameActivity activity, int layout, LayoutParams params,
			String name) {
		this(activity, layout, params, name, null);
	}

	public AbsShow(FrameActivity activity, int layout, LayoutParams params,
			String name, AbsShow faView) {
		this.activity = activity;
		this.view = View.inflate(activity, layout, null);
		this.name = name;
		this.params = params;
		this.faView = faView;
	}

	public abstract View initView();

	public void show() {
		ViewGroup llc = (ViewGroup) activity
				.findViewById(R.id.linearLayoutContent);
		llc.removeAllViews();
		if (params == null) {
			llc.addView(initView(), new LayoutParams(-1,
					-1));
		} else {
			llc.addView(initView(), params);
		}
		currentView=this;
	}

	public void jumpTo(AbsShow v) {
		v.show();
	}
}
